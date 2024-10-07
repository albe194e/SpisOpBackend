package com.example.spisopbackend.service;

import com.example.spisopbackend.Exceptions.RepositoryException;
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

   public Iterable<Company> getAllCompanies() {
        return companyRepo.findAll();
    }
    public Company createCompany(Company company) {
        return companyRepo.save(company);
    }

    public Optional<Company> updateCompany(int id, Company companyDetails) {

        Optional<Company> foundCompany = companyRepo.findById(id);

        if (foundCompany.isEmpty()) {
            return null;
        }
        foundCompany.ifPresent(company -> company.updateCompany(companyDetails));
        Company updatedCompany = companyRepo.save(foundCompany.get());
        return Optional.of(companyRepo.save(foundCompany.get()));
    }

    public void deleteCompany(int id) throws RepositoryException {
        // Check if user exists, throw exception if not
        Optional<Company> foundCompany = companyRepo.findById(id);
        foundCompany.orElseThrow();

        // Delete user
        companyRepo.deleteById(id);

        // Check if user was deleted
        Optional<Company> deletedCompany = companyRepo.findById(id);
        if (deletedCompany.isPresent()) {
            throw new RepositoryException("Company was not deleted");
        }
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
