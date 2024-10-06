package com.example.spisopbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spisopbackend.Exceptions.ResourceNotFoundException;
import com.example.spisopbackend.dto.InvitationDTO;
import com.example.spisopbackend.model.Community;
import com.example.spisopbackend.model.Invitation;
import com.example.spisopbackend.model.Organization;
import com.example.spisopbackend.model.User;
import com.example.spisopbackend.repository.OrganizationRepo;
import com.example.spisopbackend.repository.InvitationRepo;
import com.example.spisopbackend.repository.UserRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvitationService {
  
  @Autowired
  private InvitationRepo invitationRepo;
  @Autowired
  private UserRepo userRepo;
  @Autowired
  private OrganizationRepo orgRepo;

  public List<Invitation> getUserInvitations(String userId) {

    return invitationRepo.findAllByUserId(userId);
  }
  public void sendInvitation(String userId, int orgId) {

    Optional<User> user = userRepo.findById(userId);
    Optional<Organization> org = orgRepo.findById(orgId);

    if (user.isEmpty()){
      throw new ResourceNotFoundException(String.format("Could not find user with id: " + userId));
    }
    if (org.isEmpty()){
      throw new ResourceNotFoundException(String.format("Could not find organization with id: " + orgId));
    }

    Invitation i = new Invitation(user.get(), org.get());

    invitationRepo.save(i);

  }


  public InvitationDTO toDto(Invitation i) {

    return new InvitationDTO(i.getId(),
                             i.getOrganization().getName(),
                             i.getAccepted());
  }
  public void accept(int id) {

    Optional<Invitation> inviteO = invitationRepo.findById(id);

    if (inviteO.isEmpty()) {
      throw new ResourceNotFoundException("Could not find invitation from id: " + id);
    }

    Invitation i = inviteO.get();
    Organization org = i.getOrganization();
    User user = i.getUser();

    user.addOrganization(org);

    userRepo.save(user);

  }
}
