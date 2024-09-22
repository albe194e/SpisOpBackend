package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.Address;
import com.example.spisopbackend.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class CompanyDTO {
    private int id;
    private String name;
    private Address address; // Reference to AddressDto
    private Set<User> managers; // Many-to-Many relationship
}
