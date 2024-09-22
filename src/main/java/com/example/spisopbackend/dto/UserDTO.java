package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.Address;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String id;
    private String firstName, lastName, email, username, profilePicture;
    private boolean isAdmin;
    private Address address; // Reference to AddressDto
    private Set<Community> communities; // Many-to-Many relationship
    private Set<Company> companies; // Many-to-Many relationship
}
