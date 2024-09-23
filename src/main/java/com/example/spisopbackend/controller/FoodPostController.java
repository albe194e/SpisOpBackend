package com.example.spisopbackend.controller;

import com.example.spisopbackend.dto.FoodPostDTO;
import com.example.spisopbackend.model.FoodPost;
import com.example.spisopbackend.service.FoodPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodPostController {

    @Autowired
    private FoodPostService foodPostService;

    //-----------------GET-----------------\\
    @GetMapping("/foodposts")
    public List<FoodPostDTO> getFoodPosts() {
        List<FoodPost> foodPosts = foodPostService.getFoodPosts();

        System.out.println("1111 foodPosts");

        return foodPosts.stream().map(foodPost -> foodPostService.toDto(foodPost)).toList();
    }

    @GetMapping("/foodposts/{id}")
    public FoodPostDTO getFoodPostById(@PathVariable int id) {
        FoodPost foodPost = foodPostService.getFoodPostById(id);
        return foodPostService.toDto(foodPost);
    }

    @GetMapping("/foodposts/user/{userId}")
    public List<FoodPostDTO> getFoodPostsByUserId(@PathVariable String userId) {
        List<FoodPost> foodPosts = foodPostService.getFoodPostsByUserId(userId);
        return foodPosts.stream().map(foodPost -> foodPostService.toDto(foodPost)).toList();
    }

    //-----------------POST----------------\\
    @PostMapping("/foodposts")
    public FoodPostDTO saveFoodPost(@RequestBody FoodPost foodPost) {
        FoodPost savedFoodPost = foodPostService.saveFoodPost(foodPost);
        return foodPostService.toDto(savedFoodPost);
    }

    //-----------------PUT-----------------\\
    @PutMapping("/foodposts/{id}")
    public FoodPostDTO updateFoodPost(@PathVariable int id, @RequestBody FoodPost foodPostDetails) {

        FoodPost updatedFoodPost = foodPostService.updateFoodPost(id, foodPostDetails);
        return foodPostService.toDto(updatedFoodPost);
    }
    //-----------------DELETE--------------\\
    @DeleteMapping("/foodposts/{id}")
    public void deleteFoodPost(@PathVariable int id) {
        foodPostService.deleteFoodPost(id);
    }
}
