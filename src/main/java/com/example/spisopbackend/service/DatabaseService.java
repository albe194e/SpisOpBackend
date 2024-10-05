package com.example.spisopbackend.service;

import com.example.spisopbackend.model.*;
import com.example.spisopbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class DatabaseService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommunityRepo communityRepo;

    @Autowired
    private FoodPostRepo foodPostRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Transactional
    public void resetDatabase() {
        // Remove associations before deleting the entities
        companyRepo.findAll().forEach(company -> {
            company.getManagers().clear();  // Clear the managers from the company
            companyRepo.save(company);      // Save the company after clearing managers
        });

        communityRepo.findAll().forEach(community -> {
            community.getUsers().clear();  // Clear the users from the community
            communityRepo.save(community); // Save the community after clearing users
        });

        // Now safe to delete the entities
        foodPostRepo.deleteAll();
        communityRepo.deleteAll();
        companyRepo.deleteAll();
        userRepo.deleteAll();

        // Seed admin user
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@spisop.dk");
        admin.setUsername("admin");
        admin.setId("wix5XvgyXiSIt6RE6baZb7pNJ7T2");
        admin.setAdmin(true);
        userRepo.save(admin);

        // Create and save an Address
        Address address = new Address();
        address.setStreetName("Main Street");
        address.setPostalCode(12345);
        address.setCity("Copenhagen");
        address.setHouseNumber("123A");
        address.setCountry("Denmark");
        address.setCountryCode("DK");

        addressRepo.save(address);  // Save the address first

        // Create and save the Company
        Company company = new Company("SpisOp Catering");

        // Create a Set of managers and add the admin user to it
        Set<User> managers = new HashSet<>();
        managers.add(admin);
        company.setManagers(managers); // Set admin as a manager

        companyRepo.save(company);  // Now save the company

        // Seed example community
        Community community = new Community("Food Lovers", admin);
        Set<User> users = new HashSet<>();
        users.add(admin);
        community.setUsers(users);
        communityRepo.save(community);

        // Seed example food post
        FoodPost foodPost = new FoodPost("Matador Pizza", "Delicious matador pizza with fresh ingredients", new BigDecimal("35"));
        foodPost.setAuthorUser(admin);
        foodPost.setCommunity(community);
        foodPost.setCreatedAt(LocalDateTime.now());
        foodPost.setLastUpdated(LocalDateTime.now());
        foodPost.setImage("https://farumpizzaburgerhouse.dk/files/mad/pizza-1.png");
        foodPostRepo.save(foodPost);

        // Seed example food post 2
        FoodPost foodPost2 = new FoodPost("Hawaii Pizza", "Delicious hawaii pizza with fresh ingredients", new BigDecimal("40"));
        foodPost2.setAuthorCompany(company);
        foodPost2.setCreatedAt(LocalDateTime.now());
        foodPost2.setLastUpdated(LocalDateTime.now());
        foodPost2.setImage("https://dagrofa-dam.s3.eu-central-1.amazonaws.com/PROD/600x600/4001724039082.600x600.jpg");
        foodPostRepo.save(foodPost2);
    }
}
