package com.example.spisopbackend.service;

import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.CommunityRepo;
import com.example.spisopbackend.repository.FoodPostRepo;
import com.example.spisopbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommunityRepo communityRepo;

    @Autowired
    private FoodPostRepo foodPostRepo;

    public void resetDatabase() {
        // Drop existing tables
        userRepo.deleteAll();
        communityRepo.deleteAll();
        foodPostRepo.deleteAll();

        // Seed the database
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@spisop.dk");
        admin.setUsername("admin");
        admin.setId("wix5XvgyXiSIt6RE6baZb7pNJ7T2");
        admin.setAdmin(true);

        userRepo.save(admin);

    }
}
