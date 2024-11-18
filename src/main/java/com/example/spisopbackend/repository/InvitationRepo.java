package com.example.spisopbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spisopbackend.model.Invitation;

public interface InvitationRepo extends JpaRepository<Invitation, Integer> {

  
  List<Invitation> findAllByUserId(String userId);

}
