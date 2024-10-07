package com.example.spisopbackend.service;

import com.example.spisopbackend.dto.FoodPostDTO;
import com.example.spisopbackend.model.FoodPost;
import com.example.spisopbackend.repository.FoodPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        foodPostDTO.setOrganization(foodPost.getOrganization());
        foodPostDTO.setAllergies(foodPost.getAllergies());
        foodPostDTO.setLastUpdated(foodPost.getLastUpdated());
        return foodPostDTO;
    }

    public List<FoodPost> getFoodPosts() {
        return foodPostRepo.findAll();
    }

    public FoodPost getFoodPostById(int id) {
        return foodPostRepo.findById(id).orElse(null);
    }

    public List<FoodPost> getFoodPostsByUserId(String userId) {
        return foodPostRepo.findByAuthorUserId(userId);
    }

    public List<FoodPost> getCummunityFoodPosts(int id) {

        return foodPostRepo.findByOrganization_Id(id);
    }

    public FoodPost saveFoodPost(FoodPost foodPost) {
        return foodPostRepo.save(foodPost);
    }

    public FoodPost updateFoodPost(int id, FoodPost foodPostDetails) {
        Optional<FoodPost> foundFoodPost = foodPostRepo.findById(id);

        if (foundFoodPost.isEmpty()) {
            return null;
        }
        foundFoodPost.ifPresent(foodPost ->  foodPost.updateFoodPost(foodPostDetails));

        return foodPostRepo.save(foundFoodPost.get());
    }
    public void deleteFoodPost(int id) {
        foodPostRepo.deleteById(id);
    }


}
