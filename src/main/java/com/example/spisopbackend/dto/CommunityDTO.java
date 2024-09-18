package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class CommunityDTO {
    private String id;
    private String name;
    private User createdBy; // Reference to UserDto
    private Set<User> users; // Many-to-Many relationship
}
