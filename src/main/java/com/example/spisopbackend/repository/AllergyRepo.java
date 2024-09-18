package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepo extends JpaRepository<Allergy, Integer> {

}
