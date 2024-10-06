package com.example.spisopbackend.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "Company")
public class Company extends Organization{

    // Company-Managers Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "Company_Managers",
            joinColumns = @JoinColumn(name = "companyId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @JsonBackReference
    private Set<User> managers;

    public Company(String name) {
        this.setName(name);
    }
    public Company() {
    }

    public void updateCompany(Company companyDetails) {

        this.id = companyDetails.getId();
        this.name = companyDetails.getName();
        this.address = companyDetails.getAddress();
        this.managers = companyDetails.getManagers();
    }


}
