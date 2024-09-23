package com.example.spisopbackend.controller;

import com.example.spisopbackend.dto.CompanyDTO;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //-----------------GET-----------------\\
    @GetMapping("/company/{id}")
    public CompanyDTO getCompanyById(@PathVariable int id) {

        Company company = companyService.getCompanyById(id);
        return companyService.toDto(company);
    }
    //-----------------POST----------------\\
    @PostMapping("/company")
    public CompanyDTO createCompany(@RequestBody Company company) {

        Company newCompany = companyService.createCompany(company);
        return companyService.toDto(newCompany);
    }
    //-----------------PUT-----------------\\..
    @PutMapping("/company/{id}")
    public CompanyDTO updateCompany(@PathVariable int id, @RequestBody Company companyDetails) {

        Company updatedCompany = companyService.updateCompany(id, companyDetails);
        return companyService.toDto(updatedCompany);
    }
    //-----------------DELETE--------------\\
    @DeleteMapping("company/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
