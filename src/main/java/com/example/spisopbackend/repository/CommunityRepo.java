package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommunityRepo extends JpaRepository<Community, Integer> {

    List<Community> findCommunitiesByCreatedBy_Id(String createdBy_id);
    List<Community> findCommunitiesByUsers_Id(String members_id);
}
