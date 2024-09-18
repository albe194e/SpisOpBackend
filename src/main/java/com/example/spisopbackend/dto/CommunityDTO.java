package com.example.spisopbackend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CommunityDTO {
    private String id;
    private String name;
    private UserDTO createdBy; // Reference to UserDto
    private Set<UserDTO> users; // Many-to-Many relationship
}
