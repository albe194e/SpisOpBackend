package com.example.spisopbackend.model.notifications;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.spisopbackend.model.FoodPost;

@Data
@Entity
@Table(name = "Notification")
public class Notification {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;
    
    @ManyToOne
    @JoinColumn(name = "postId")
    private FoodPost post;
}
