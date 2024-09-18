package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepo extends JpaRepository<Community, Integer> {

}
