package com.example.spisopbackend.controller;

import com.example.spisopbackend.dto.UserDTO;
import com.example.spisopbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.spisopbackend.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable int id) {

        User user = userService.getUserById(id);
        return userService.toDto(user);
    }

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody User user) {

        User newUser = userService.createUser(user);
        return userService.toDto(newUser);
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody User userDetails) {

        User updatedUser = userService.updateUser(id, userDetails);
        return userService.toDto(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
