package com.example.spisopbackend.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Community")
public class Community {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "createdById")
    private User createdBy;

    // Users in the Community
    @ManyToMany(mappedBy = "communities")
    private Set<User> users;

    public Community(String name, User createdBy) {
        this.setName(name);
        this.createdBy = createdBy;
        this.users = Set.of();
    }
    public Community() {
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void setName(String name) {

        //Add validation here
        this.name = name;
    }


}
