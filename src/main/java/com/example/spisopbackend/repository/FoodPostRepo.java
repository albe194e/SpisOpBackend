package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.FoodPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodPostRepo extends JpaRepository<FoodPost, Integer> {

}
