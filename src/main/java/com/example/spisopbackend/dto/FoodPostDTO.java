package com.example.spisopbackend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class FoodPostDTO {
    private String id;
    private String image;
    private String title;
    private String description;
    private BigDecimal price;
    private UserDTO authorUser; // Reference to UserDto
    private CompanyDTO authorCompany; // Reference to CompanyDto
    private CommunityDTO community; // Reference to CommunityDto
    private Set<AllergyDTO> allergies; // Many-to-Many relationship
}
