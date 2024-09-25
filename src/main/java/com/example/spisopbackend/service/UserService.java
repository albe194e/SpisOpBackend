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

    //
    @Autowired
    private UserRepo userRepo;
    public Optional<User> getUserById(String id) {

        Optional<User> foundUser = userRepo.findById(id);
        return foundUser;

    }

    public Optional<User> createUser(User user) {
        User savedUser = userRepo.save(user);
        return Optional.ofNullable(savedUser);
    }

    public User updateUser(String id, User userDetails) {

        Optional<User> foundUser = userRepo.findById(id);

        if (foundUser.isEmpty()) {
            return null;
        }
        foundUser.ifPresent(user -> user.updateUser(userDetails));

        return userRepo.save(foundUser.get());
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setProfilePicture(user.getProfilePicture());
        userDTO.setAdmin(user.isAdmin());
        userDTO.setAddress(user.getAddress());
        userDTO.setCommunities(user.getCommunities());
        userDTO.setCompanies(user.getCompanies());
        return userDTO;
    }
}
