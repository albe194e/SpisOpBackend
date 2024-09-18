package com.example.spisopbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(length = 255)
    private String firstName;

    @Column(length = 255)
    private String lastName;

    @Column(length = 255)
    private String email;

    @Column(length = 255)
    private String username;

    @Column(length = 255)
    private String profilePicture;

    @Column
    private boolean isAdmin;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    // User-Community Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "User_CommunityGroup",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "communityGroupId")
    )
    private Set<Community> communities;

    // User-Company Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "User_Company",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "companyId")
    )
    private Set<Company> companies;

    public void updateUser(User userDetails) {

        this.email = userDetails.getEmail();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.username = userDetails.getUsername();
        this.profilePicture = userDetails.getProfilePicture();
        this.isAdmin = userDetails.isAdmin();
        this.address = userDetails.getAddress();
        this.communities = userDetails.getCommunities();
        this.companies = userDetails.getCompanies();

    }
}

