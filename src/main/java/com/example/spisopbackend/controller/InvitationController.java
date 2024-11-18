package com.example.spisopbackend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.dto.InvitationDTO;
import com.example.spisopbackend.model.Invitation;
import com.example.spisopbackend.service.InvitationService;

@RestController
public class InvitationController {

  @Autowired
  private InvitationService invitationService;

  @GetMapping("/user/{userId}/invitations")
  public ResponseEntity<List<InvitationDTO>> getUserInvitations(@PathVariable String userId) {

    List<Invitation> invitations = invitationService.getUserInvitations(userId);
    return ResponseEntity.ok().body(invitations.stream().map(invitation -> invitationService.toDto(invitation)).toList());
  }

  @PostMapping("/invite/{userId}/org/{orgId}")
  public void sendInvitationToUser(@PathVariable String userId, @PathVariable int orgId) {

    invitationService.sendInvitation(userId, orgId);
  }

  @PostMapping("/accept/invite/{id}")
  public ResponseEntity<Void> acceptInvite(@PathVariable int id){

    try {
        invitationService.accept(id);
        return ResponseEntity.ok().build();
    } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
