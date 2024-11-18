package com.example.spisopbackend.dto.foodpost;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewFoodPostDTO {
    
    private String image, title, description;
    private BigDecimal price;
    private String authorUserId;
    private LocalDateTime createdAt, lastUpdated;
    private int organizationId;
    //Allergies
}
