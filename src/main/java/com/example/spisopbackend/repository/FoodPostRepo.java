package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.FoodPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodPostRepo extends JpaRepository<FoodPost, Integer> {

    List<FoodPost> findByUserId(int userId);

}
