package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunityRepo extends JpaRepository<Community, Integer> {

    List<Community> findCommunitiesByCreatedBy_Id(String createdById);
    List<Community> findCommunitiesByUsers_Id(String membersId);

}
