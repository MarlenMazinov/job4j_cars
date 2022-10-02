package ru.job4j.cars.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private Date created;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;
}
