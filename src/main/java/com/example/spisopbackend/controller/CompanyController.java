package com.example.spisopbackend.controller;

import com.example.spisopbackend.Exceptions.RepositoryException;
import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.dto.CompanyDTO;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //-----------------GET-----------------\\
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable int id) {
        try {
            Company company = companyService.getCompanyById(id);
            return ResponseEntity.ok(companyService.toDto(company));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/company")
    public ResponseEntity<Iterable<Company>> getAllCompanies() {
        try {
            return ResponseEntity.ok(companyService.getAllCompanies());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //-----------------POST----------------\\
    @PostMapping("/company")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody Company company) {
        try {
            Company newCompany = companyService.createCompany(company);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(companyService.toDto(newCompany));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //-----------------PUT-----------------\\..
    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable int id, @RequestBody Company companyDetails) {
        try {
            Optional<Company> updatedCompany = companyService.updateCompany(id, companyDetails);
            return ResponseEntity.ok()
                    .body(companyService.toDto(updatedCompany.orElseThrow()));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
        //-----------------DELETE--------------\\
        @DeleteMapping("/company/{id}")
        public ResponseEntity<Void> deleteCompany(@PathVariable int id) {

            try {
                companyService.deleteCompany(id);
                return ResponseEntity.ok().build();
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
}