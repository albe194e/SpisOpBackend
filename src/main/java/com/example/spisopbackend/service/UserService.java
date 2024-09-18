package com.example.spisopbackend.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.spisopbackend.dto.UserDTO;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    public UserDTO getUserById(int id) {

        UserDTO userDTO = new UserDTO();

        Optional<User> foundUser = userRepo.findById(id);
        if (foundUser.isEmpty()) {
            return null;
        }
        userDTO.convertToUserDTO(foundUser.get());
        return userDTO;
    }

    public UserDTO createUser(User user) {

        User savedUser = userRepo.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.convertToUserDTO(savedUser);
        return userDTO;
    }

    public UserDTO updateUser(int id, User userDetails) {

        Optional<User> foundUser = userRepo.findById(id);
        UserDTO userDTO = new UserDTO();

        if (foundUser.isEmpty()) {
            return null;
        }
        foundUser.ifPresent(user -> user.updateUser(userDetails));
        userRepo.save(foundUser.get());
        userDTO.convertToUserDTO(foundUser.get());
        return userDTO;
    }

    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
