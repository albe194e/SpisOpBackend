package com.example.spisopbackend.service;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ResourceNotFoundException;
import com.example.spisopbackend.dto.foodpost.FoodPostDTO;
import com.example.spisopbackend.dto.foodpost.NewFoodPostDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.FoodPost;
import com.example.spisopbackend.model.Organization;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.FoodPostRepo;
import com.example.spisopbackend.repository.OrganizationRepo;
import com.example.spisopbackend.repository.UserRepo;

import org.hibernate.id.OptimizableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodPostService {

    @Autowired
    private FoodPostRepo foodPostRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrganizationRepo organizationRepo;

    public List<FoodPostDTO> getFoodPosts() {
        return foodPostRepo.findAll().stream().map(fp -> toDto(fp)).toList();
    }

    public FoodPostDTO getFoodPostById(int id) {
        Optional<FoodPost> fp = foodPostRepo.findById(id);
        if (fp.isEmpty()) {
            throw new ResourceNotFoundException("Could not find Food post by: " + id);
        }
        return toDto(fp.get());
    }

    public List<FoodPostDTO> getFoodPostsByUserId(String userId) {
        return foodPostRepo.findByAuthorUserId(userId).stream().map(fp -> toDto(fp)).toList();
    }

    public List<FoodPostDTO> getCummunityFoodPosts(int id) {
        return foodPostRepo.findByOrganization_Id(id).stream().map(fp -> toDto(fp)).toList();
    }

    public FoodPostDTO saveFoodPost(NewFoodPostDTO fp) {
        
        FoodPost foodPost = toEntity(fp);
        return toDto(foodPostRepo.save(foodPost));
    }

    public FoodPostDTO updateFoodPost(int id, NewFoodPostDTO foodPostDetails) {
        Optional<FoodPost> foundFoodPost = foodPostRepo.findById(id);

        if (foundFoodPost.isEmpty()) {
            return null;
        }
        FoodPost newFp = toEntity(foodPostDetails);
        foundFoodPost.ifPresent(foodPost ->  foodPost.update(newFp));
        FoodPost updatedFoodPost = foodPostRepo.save(foundFoodPost.get());

        return toDto(updatedFoodPost);
    }
    public void deleteFoodPost(int id) throws RepositoryException {
        Optional<FoodPost> foundFp = foodPostRepo.findById(id);
        if (foundFp.isEmpty()) {
            throw new ResourceNotFoundException("Could not find foodpost: " + id + " to delete");
        }

        foodPostRepo.deleteById(id);

        Optional<FoodPost> deletedFp = foodPostRepo.findById(id);
        if (deletedFp.isPresent()) {
            throw new RepositoryException("Community was not deleted");
        }
    }

    //Helpers
    private FoodPostDTO toDto(FoodPost fp) {
        FoodPostDTO foodPostDTO = new FoodPostDTO();
        foodPostDTO.setId(fp.getId());
        foodPostDTO.setImage(fp.getImage());
        foodPostDTO.setTitle(fp.getTitle());
        foodPostDTO.setDescription(fp.getDescription());
        foodPostDTO.setPrice(fp.getPrice());
        foodPostDTO.setAuthorUser(fp.getAuthorUser());
        foodPostDTO.setOrganization(fp.getOrganization());
        foodPostDTO.setAllergies(fp.getAllergies());
        foodPostDTO.setLastUpdated(fp.getLastUpdated());
        return foodPostDTO;
    }

    private FoodPost toEntity(NewFoodPostDTO dto) {
        FoodPost fp = new FoodPost();
        fp.setTitle(dto.getTitle());
        fp.setDescription(dto.getDescription());
        fp.setImage(dto.getImage());
        fp.setPrice(dto.getPrice());
        
        Optional<User> author = userRepo.findById(dto.getAuthorUserId());
        Optional<Organization> org = organizationRepo.findById(dto.getOrganizationId());
        if (author.isEmpty()){
            throw new ResourceNotFoundException("Could not find author (user)");
        }
        if (org.isEmpty()){
            throw new ResourceNotFoundException("Could not find organization by id: " + dto.getOrganizationId());
        }

        fp.setAuthorUser(author.get());
        fp.setOrganization(org.get());

        return fp;
    }
}
