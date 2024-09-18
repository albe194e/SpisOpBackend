package com.example.spisopbackend.service;

import com.example.spisopbackend.dto.FoodPostDTO;
import com.example.spisopbackend.model.FoodPost;
import com.example.spisopbackend.repository.FoodPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodPostService {

    @Autowired
    private FoodPostRepo foodPostRepo;

    public FoodPostDTO toDto(FoodPost foodPost) {
        FoodPostDTO foodPostDTO = new FoodPostDTO();
        foodPostDTO.setId(foodPost.getId());
        foodPostDTO.setImage(foodPost.getImage());
        foodPostDTO.setTitle(foodPost.getTitle());
        foodPostDTO.setDescription(foodPost.getDescription());
        foodPostDTO.setPrice(foodPost.getPrice());
        foodPostDTO.setAuthorUser(foodPost.getAuthorUser());
        foodPostDTO.setAuthorCompany(foodPost.getAuthorCompany());
        foodPostDTO.setCommunity(foodPost.getCommunity());
        foodPostDTO.setAllergies(foodPost.getAllergies());
        return foodPostDTO;
    }

    public void getFoodPosts() {
        foodPostRepo.findAll();
    }

    public void getFoodPostById(int id) {
        foodPostRepo.findById(id);
    }

    public void getFoodPostsByUserId(int userId) {
        foodPostRepo.findByUserId(userId);
    }

    public void saveFoodPost(FoodPost foodPost) {
        foodPostRepo.save(foodPost);
    }
}
