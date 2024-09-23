package com.example.spisopbackend.service;

import com.example.spisopbackend.dto.CompanyDTO;
import com.example.spisopbackend.model.Company;
import com.example.spisopbackend.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public Company getCompanyById(int id) {

        Optional<Company> foundCompany = companyRepo.findById(id);
        return foundCompany.orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public Company updateCompany(int id, Company companyDetails) {

        Optional<Company> foundCompany = companyRepo.findById(id);

        if (foundCompany.isEmpty()) {
            return null;
        }
        foundCompany.ifPresent(company -> company.updateCompany(companyDetails));

        return companyRepo.save(foundCompany.get());
    }

    public  void deleteCompany(int id) {
        companyRepo.deleteById(id);
    }

    public CompanyDTO toDto(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        companyDTO.setAddress(company.getAddress());
        companyDTO.setManagers(company.getManagers());
        return companyDTO;
    }
}
