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
    private String id;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "createdById")
    private User createdBy;

    // Users in the Community
    @ManyToMany(mappedBy = "communities")
    private Set<User> users;
}
