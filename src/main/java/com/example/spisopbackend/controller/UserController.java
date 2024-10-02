package com.example.spisopbackend.controller;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.dto.UserDTO;
import com.example.spisopbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.spisopbackend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //-----------------GET-----------------\\
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {

        try {
            Optional<User> user = userService.getUserById(id);
            return ResponseEntity.ok(userService.toDto(user.orElseThrow()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //-----------------POST----------------\\
    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {

        try {
            Optional<User> newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.toDto(newUser.orElseThrow()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/user/{userId}/join/community/{communityId}")
    public ResponseEntity<Void> addUserToCommunity(@PathVariable String userId, @PathVariable int communityId) throws RepositoryException {

        try {
            userService.addUserToCommunity(userId, communityId);
            return ResponseEntity.ok().build();
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    //-----------------PUT-----------------\\ fsdf
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody User userDetails) {

        try {
            Optional<User> updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok()
                    .body(userService.toDto(updatedUser.orElseThrow()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //-----------------DELETE--------------\\
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {

        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
