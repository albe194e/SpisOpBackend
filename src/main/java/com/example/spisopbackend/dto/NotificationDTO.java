package com.example.spisopbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private String id;
    private LocalDateTime timestamp;
    private FoodPostDTO post; // Reference to FoodPostDto
}
