package com.example.spisopbackend.controller;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ResourceNotFoundException;
import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.dto.foodpost.FoodPostDTO;
import com.example.spisopbackend.dto.foodpost.NewFoodPostDTO;
import com.example.spisopbackend.service.FoodPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class FoodPostController {

    @Autowired
    private FoodPostService foodPostService;

    //-----------------GET-----------------\\
    @GetMapping("/foodposts")
    public List<FoodPostDTO> getFoodPosts() {
        return foodPostService.getFoodPosts();
    }

    @GetMapping("/foodposts/{id}")
    public ResponseEntity<FoodPostDTO> getFoodPostById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(foodPostService.getFoodPostById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/foodposts/user/{userId}")
    public ResponseEntity<List<FoodPostDTO>> getFoodPostsByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(foodPostService.getFoodPostsByUserId(userId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/community/{id}/foodposts")
    public ResponseEntity<List<FoodPostDTO>> getCommunityFoodPosts(@PathVariable int id) {
        try {
            return ResponseEntity.ok(foodPostService.getCummunityFoodPosts(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //-----------------POST----------------\\
    @PostMapping("/foodpost")
    public ResponseEntity<FoodPostDTO> createFoodPost(@RequestBody NewFoodPostDTO foodPost) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(foodPostService.saveFoodPost(foodPost));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //-----------------PUT-----------------\\
    @PutMapping("/foodposts/{id}")
    public ResponseEntity<FoodPostDTO> updateFoodPost(@PathVariable int id, @RequestBody NewFoodPostDTO foodPostDetails) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                                 .body(foodPostService.updateFoodPost(id, foodPostDetails));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //-----------------DELETE--------------\\
    @DeleteMapping("/foodposts/{id}")
    public ResponseEntity<Void> deleteFoodPost(@PathVariable int id) {
        try {
            foodPostService.deleteFoodPost(id);
            return ResponseEntity.ok().build();
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
