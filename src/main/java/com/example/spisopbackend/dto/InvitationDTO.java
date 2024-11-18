package com.example.spisopbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvitationDTO {
  
  private int id;
  private String orgName;
  private Boolean accepted;
}
