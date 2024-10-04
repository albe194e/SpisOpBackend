package com.example.spisopbackend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Community")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Community {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "createdById")
    private User createdBy;

    @ManyToMany(mappedBy = "communities")
    private Set<User> users = new HashSet<>();

    // Constructors, setters with validation, and other methods remain unchanged


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
