package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.Address;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.Company;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String profilePicture;
    private boolean isAdmin;
    private Address address; // Reference to AddressDto
    private Set<Community> communities; // Many-to-Many relationship
    private Set<Company> companies; // Many-to-Many relationship
}
