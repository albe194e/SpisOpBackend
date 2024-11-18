package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.Address;
import com.example.spisopbackend.model.Organization;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String firstName, lastName, email, username, profilePicture;
    private boolean isAdmin;
    private Address address; // Reference to AddressDto
    private Set<Organization> organizations;
}
