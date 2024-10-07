package com.example.spisopbackend.dto.foodpost;

import com.example.spisopbackend.model.Allergy;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.model.Organization;
import com.example.spisopbackend.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class FoodPostDTO {
    private int id;
    private String image, title, description;
    private BigDecimal price;
    private User authorUser; // Reference to UserDto
    private LocalDateTime createdAt, lastUpdated;
    private Organization organization;
    private Set<Allergy> allergies; // Many-to-Many relationship
}
