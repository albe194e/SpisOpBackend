package com.example.spisopbackend.dto;

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
    private AddressDTO address; // Reference to AddressDto
    private Set<CommunityDTO> communities; // Many-to-Many relationship
    private Set<CompanyDTO> companies; // Many-to-Many relationship
}
