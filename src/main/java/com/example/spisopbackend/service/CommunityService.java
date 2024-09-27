package com.example.spisopbackend.service;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.dto.CommunityDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.CommunityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepo communityRepo;

    public Optional<Community> getCommunityById(int id) {
        return communityRepo.findById(id);
    }

    public List<Community> getCommunities() {
        return communityRepo.findAll();
    }

    public List<Community> getCommunitiesByCreatedById(String userId) {
        return communityRepo.findCommunitiesByCreatedBy_Id(userId);
    }

    public List<Community> getCommunitiesByUserId(String userId) {
        return communityRepo.findCommunitiesByUsers_Id(userId);
    }

    public Optional<Community> createCommunity(Community community) {
        return Optional.of(communityRepo.save(community));
    }

    public Optional<Community> updateCommunity(int id, Community communityDetails) {
        Community community = communityRepo.findById(id).orElse(null);
        if (community == null) {
            return Optional.empty();
        }
        community.setName(communityDetails.getName());
        community.setCreatedBy(communityDetails.getCreatedBy());
        community.setUsers(communityDetails.getUsers());
        return Optional.of(communityRepo.save(community));
    }
    public void deleteCommunity(int id) throws RepositoryException {
        Optional<Community> foundCommunity = communityRepo.findById(id);
        foundCommunity.orElseThrow();

        communityRepo.deleteById(id);

        Optional<Community> deletedCommunity = communityRepo.findById(id);
        if (deletedCommunity.isPresent()) {
            throw new RepositoryException("Community was not deleted");
        }
    }

    public CommunityDTO toDto(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(community.getId());
        communityDTO.setName(community.getName());
        communityDTO.setCreatedBy(community.getCreatedBy());
        communityDTO.setUsers(community.getUsers());
        return communityDTO;

    }


}
