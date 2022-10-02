package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "auto_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String login;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Post> posts;
}