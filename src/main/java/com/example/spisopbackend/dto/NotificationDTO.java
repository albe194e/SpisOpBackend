package com.example.spisopbackend.dto;

import com.example.spisopbackend.model.FoodPost;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private String id;
    private LocalDateTime timestamp;
    private FoodPost post; // Reference to FoodPostDto
}
