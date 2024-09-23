package com.example.spisopbackend.controller;

import com.example.spisopbackend.dto.CommunityDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    //-----------------GET-----------------\\
    @GetMapping("/community/{id}")
    public CommunityDTO getCommunityById(@PathVariable int id) {
        Community community = communityService.getCommunityById(id);
        return communityService.toDto(community);
    }
    @GetMapping("/user/{userId}/communities")
    public List<CommunityDTO> getCommunitiesByUserId(@PathVariable String userId) {
        List<Community> communities = communityService.getCommunitiesByUserId(userId);
        return communities.stream().map(community -> communityService.toDto(community)).toList();
    }

    //-----------------POST-----------------\\
    @PostMapping("/community")
    public CommunityDTO createCommunity(@RequestBody Community community) {
        Community newCommunity = communityService.createCommunity(community);
        return communityService.toDto(newCommunity);
    }

    //-----------------PUT-----------------\\
    @PutMapping("/community/{id}")
    public CommunityDTO updateCommunity(@PathVariable int id, @RequestBody Community communityDetails) {
        Community updatedCommunity = communityService.updateCommunity(id, communityDetails);
        return communityService.toDto(updatedCommunity);
    }

    //-----------------DELETE-----------------\\
    @DeleteMapping("/community/{id}")
    public void deleteCommunity(@PathVariable int id) {
        communityService.deleteCommunity(id);
    }

}
