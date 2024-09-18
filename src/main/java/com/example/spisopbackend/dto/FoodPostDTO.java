package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.Allergy;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.model.User;
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
    private User authorUser; // Reference to UserDto
    private Company authorCompany; // Reference to CompanyDto
    private Community community; // Reference to CommunityDto
    private Set<Allergy> allergies; // Many-to-Many relationship
}
