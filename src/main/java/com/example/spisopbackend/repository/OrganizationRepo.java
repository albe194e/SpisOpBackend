package com.example.spisopbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spisopbackend.model.Organization;

// NoRepositoryBean indicates that Spring Data should not create instances for this interface
@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Integer> {
    
}