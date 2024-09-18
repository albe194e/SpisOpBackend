package com.example.spisopbackend.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(length = 255)
    private String streetName;

    @Column
    private int postalCode;

    @Column(length = 255)
    private String city;

    @Column(length = 50)
    private String houseNumber;

    @Column(length = 10)
    private String countryCode;

    @Column(length = 255)
    private String country;
}
