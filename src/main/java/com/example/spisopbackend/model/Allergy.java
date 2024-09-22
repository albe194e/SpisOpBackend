package com.example.spisopbackend.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Allergy")
public class Allergy {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String description;
}

