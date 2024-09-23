package com.example.spisopbackend.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    // Company-Managers Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "Company_Managers",
            joinColumns = @JoinColumn(name = "companyId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<User> managers;

    public void updateCompany(Company companyDetails) {

        this.id = companyDetails.getId();
        this.name = companyDetails.getName();
        this.address = companyDetails.getAddress();
        this.managers = companyDetails.getManagers();
    }


}
