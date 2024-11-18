package com.example.spisopbackend.controller;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.dto.CommunityDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    //-----------------GET-----------------\\
    @GetMapping("/community/{id}")
    public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable int id) {

        try {
            Optional<Community> community = communityService.getCommunityById(id);
            return ResponseEntity.ok(communityService.toDto(community.orElseThrow()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{userId}/owmed_communities")
    public ResponseEntity<List<CommunityDTO>> getCommunitiesCreatedBy(@PathVariable String userId) {
        List<Community> communities = communityService.getCommunitiesByCreatedById(userId);
        return ResponseEntity.ok(communities.stream().map(community -> communityService.toDto(community)).toList());
    }

    @GetMapping("/user/{userId}/communities")
    public ResponseEntity<List<CommunityDTO>> getMembershipCommunities(@PathVariable String userId) {
        List<Community> communities = communityService.getCommunitiesByUserId(userId);
        return ResponseEntity.ok(communities.stream().map(community -> communityService.toDto(community)).toList());
    }
    @GetMapping("/communities")
    public ResponseEntity<List<CommunityDTO>> getCommunities() {
        List<Community> communities = communityService.getCommunities();
        return ResponseEntity.ok(communities.stream().map(community -> communityService.toDto(community)).toList());
    }

    //-----------------POST-----------------\\
    @PostMapping("/community")
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody Community community) {

        try {
            Optional<Community> newCommunity = communityService.createCommunity(community);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(communityService.toDto(newCommunity.orElseThrow()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //-----------------PUT-----------------\\
    @PutMapping("/community/{id}")
    public ResponseEntity<CommunityDTO> updateCommunity(@PathVariable int id, @RequestBody Community communityDetails) {
        try {
            Optional<Community> updatedCommunity = communityService.updateCommunity(id, communityDetails);
            return ResponseEntity.ok()
                    .body(communityService.toDto(updatedCommunity.orElseThrow()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //-----------------DELETE-----------------\\
    @DeleteMapping("/community/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable int id) {
        try {
            communityService.deleteCommunity(id);
            return ResponseEntity.ok().build();
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
