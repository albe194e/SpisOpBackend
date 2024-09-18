package com.example.spisopbackend.controller;

import com.example.spisopbackend.service.FoodPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class FoodPostController {

    @Autowired
    private FoodPostService foodPostService;

    @GetMapping("/foodposts")
    public void getFoodPosts() {
        System.out.println("Getting food posts");
    }
}
