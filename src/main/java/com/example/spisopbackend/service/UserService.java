package com.example.spisopbackend.service;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.dto.UserDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.CommunityRepo;
import com.example.spisopbackend.repository.UserRepo;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {




    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommunityRepo communityRepo;
    public Optional<User> getUserById(String id) {


        return userRepo.findById(id);

    }

    public Optional<User> createUser(User user) {

        Optional<User> existingUser = userRepo.findById(user.getId());
        if (existingUser.isPresent()) {
            throw new EntityExistsException("User already exists");
        }
        User createdUser = userRepo.save(user);

        return Optional.of(createdUser);
    }

    @Transactional
    public void addUserToCommunity(String userId, int communityId) throws RepositoryException{
        Community community = communityRepo.findById(communityId)
                .orElseThrow(() -> new NoSuchElementException("Community not found with ID: " + communityId));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));


        // Add community to user and update the relationship in community
        user.addCommunity(community);
        userRepo.save(user); // Persist the user


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
