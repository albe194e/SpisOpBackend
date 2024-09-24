package com.example.spisopbackend.controller;

import com.example.spisopbackend.dto.UserDTO;
import com.example.spisopbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.spisopbackend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //-----------------GET-----------------\\
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {

        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(userService.toDto(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //-----------------POST----------------\\
    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {

        Optional<User> newUser = userService.createUser(user);
        if (newUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(userService.toDto(newUser.get()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //-----------------PUT-----------------\\
    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable String id, @RequestBody User userDetails) {

        User updatedUser = userService.updateUser(id, userDetails);
        return userService.toDto(updatedUser);
    }

    //-----------------DELETE--------------\\
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
