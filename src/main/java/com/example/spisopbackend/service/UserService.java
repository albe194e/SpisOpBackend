package com.example.spisopbackend.service;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ValidationException;
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
    public Optional<User> getUserById(String id) {

        return userRepo.findById(id);

    }

    public Optional<User> createUser(User user) {

        User savedUser = userRepo.save(user);
        return Optional.of(savedUser);
    }

    public Optional<User> updateUser(String id, User userDetails) {

        Optional<User> foundUser = userRepo.findById(id);

        if (foundUser.isEmpty()) {
            return Optional.empty();
        }
        foundUser.ifPresent(user -> user.updateUser(userDetails));
        User updatedUser = userRepo.save(foundUser.get());
        return Optional.of(userRepo.save(updatedUser));
    }

    public void deleteUser(String id) throws RepositoryException {

        // Check if user exists, throw exception if not
        Optional<User> foundUser = userRepo.findById(id);
        foundUser.orElseThrow();

        // Delete user
        userRepo.deleteById(id);

        // Check if user was deleted
        Optional<User> deletedUser = userRepo.findById(id);
        if (deletedUser.isPresent()) {
            throw new RepositoryException("User was not deleted");
        }
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
