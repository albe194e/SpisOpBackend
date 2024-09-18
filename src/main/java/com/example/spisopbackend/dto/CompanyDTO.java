package com.example.spisopbackend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CompanyDTO {
    private String id;
    private String name;
    private AddressDTO address; // Reference to AddressDto
    private Set<UserDTO> managers; // Many-to-Many relationship
}
