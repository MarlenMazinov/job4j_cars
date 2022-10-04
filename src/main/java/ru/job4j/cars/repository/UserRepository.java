package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        Optional<User> fuser = findByLogin(user.getLogin());
        fuser.ifPresent(value -> user.setId(value.getId()));
        return user;
    }

    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE User SET login = :flogin, password = :fpassword "
                                    + "WHERE id = :fid")
                    .setParameter("flogin", user.getLogin())
                    .setParameter("fpassword", user.getPassword())
                    .setParameter("fid", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE User WHERE id = :fid")
                    .setParameter("fid", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery(
                    "FROM User u ORDER BY u.id DESC").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }

    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        Optional<User> result = Optional.empty();
        try {
            session.beginTransaction();
            result = session.createQuery(
                    "from User u where u.id = " + id).uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }

    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> result = new ArrayList<>();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from User u where u.login like :fkey");
            query.setParameter("fkey", "%" + key + "%");
            result = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }

    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> result = Optional.empty();
        try {
            session.beginTransaction();
            Query query = session.createQuery(
                    "from User u where u.login = :flogin");
            query.setParameter("flogin", login);
            result = query.uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return result;
    }
}