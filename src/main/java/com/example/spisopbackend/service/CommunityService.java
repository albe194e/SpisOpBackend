package com.example.spisopbackend.service;

import com.example.spisopbackend.dto.CommunityDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.CommunityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepo communityRepo;

    public Community getCommunityById(int id) {
        return communityRepo.findById(id).orElse(null);
    }
    public Community createCommunity(Community community) {
        return communityRepo.save(community);
    }
    public Community updateCommunity(int id, Community communityDetails) {
        Community community = communityRepo.findById(id).orElse(null);
        if (community == null) {
            return null;
        }
        community.setName(communityDetails.getName());
        community.setCreatedBy(communityDetails.getCreatedBy());
        community.setUsers(communityDetails.getUsers());
        return communityRepo.save(community);
    }
    public void deleteCommunity(int id) {
        communityRepo.deleteById(id);
    }

    public CommunityDTO toDto(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setId(community.getId());
        communityDTO.setName(community.getName());
        communityDTO.setCreatedBy(community.getCreatedBy());
        communityDTO.setUsers(community.getUsers());
        return communityDTO;

    }

    public List<Community> getCommunitiesByUserId(String userId) {
        return communityRepo.findCommunitiesByCreatedBy_Id(userId);
    }
}
