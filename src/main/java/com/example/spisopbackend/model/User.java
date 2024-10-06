package com.example.spisopbackend.model;

import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.utils.enums.AcceptedImageFormats;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "User")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
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

    @ManyToMany
    @JoinTable(
            name = "User_Organizations",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "organizationId")
    )
    @JsonManagedReference
    private Set<Organization> organizations = new HashSet<>(); // This can hold both Community and Company


    public User(String id, String firstName, String lastName, String email, String username, String profilePicture, boolean isAdmin) {
        this.id = id;
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setUsername(username);
        this.setProfilePicture(profilePicture);
        this.setAdmin(isAdmin);
    }
    public User() {}

    public void setFirstName(String firstName) {

        if (firstName == null || firstName.isEmpty()) {
            throw new ValidationException("First name cannot be empty");
        }
        if (firstName.length() > 30) {
            throw new ValidationException("First name cannot be longer than 30 characters");
        }
        if (!firstName.matches("^[a-zA-ZÆØÅæøå]+$")) {
            throw new ValidationException("First name can only contain letters");
        }
        if (firstName.contains(" ")) {
            throw new ValidationException("First name cannot contain spaces");
        }
        if (firstName.length() < 2) {
            throw new ValidationException("First name must be at least 2 characters long");
        }
        // Invalid Capitalization
        if (!firstName.substring(0, 1).matches("^[A-ZÆØÅ]")) {
            throw new ValidationException("First name must start with a capital letter");
        }
        // only the first letter is capitalized
        if (!firstName.substring(1).matches("^[a-zæøå]+$")) {
            throw new ValidationException("First name must start with a capital letter followed by lowercase letters");
        }
        this.firstName = firstName;
    }

    public void setEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }
        if (email.length() > 255) {
            throw new ValidationException("Email cannot be longer than 255 characters");
        }

        if (email.split("@")[0].length() > 64) {
            throw new ValidationException("Email cannot be longer than 64 characters before @");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationException("Email format not valid. Valid format: example@yourdomain.yourtlf");
        }
        this.email = email;
    }

    public void setLastName(String lastName) {

        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException("Last name cannot be empty");
        }
        if (lastName.length() > 30) {
            throw new ValidationException("Last name cannot be longer than 30 characters");
        }
        if (!lastName.matches("^[a-zA-ZÆØÅæøå ]+$")) {
            throw new ValidationException("Last name can only contain letters");
        }

        if (lastName.length() < 2) {
            throw new ValidationException("Last name must be at least 2 characters long");
        }
        // Invalid Capitalization
        if (!lastName.substring(0, 1).matches("^[A-ZÆØÅ]")) {
            throw new ValidationException("Last name must start with a capital letter");
        }
        // Check multiple names for capital letter
        if (lastName.contains(" ")) {
            String[] names = lastName.split(" ");
            for (String name : names) {
                if (!name.substring(0, 1).matches("^[A-ZÆØÅ]")) {
                    throw new ValidationException("Last names must start with a capital letter");
                }
                if (!name.substring(1).matches("^[a-zæøå]+$")) {
                    throw new ValidationException("Last names must start with a capital letter followed by lowercase letters");
                }
            }
        } else {
            if (!lastName.substring(1).matches("^[a-zæøå]+$")) {
                throw new ValidationException("Last name must start with a capital letter followed by lowercase letters");
            }
        }

        this.lastName = lastName;
    }

    public void setUsername(String username) {

        if (username == null || username.isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (username.length() > 30) {
            throw new ValidationException("Username cannot be longer than 30 characters");
        }
        if (username.length() < 4) {
            throw new ValidationException("Username must be at least 4 characters long");
        }
        if (!username.matches("^[a-zA-Z0-9æøåÆØÅ]+$")) {
            throw new ValidationException("Username can only contain letters and numbers");
        }

        this.username = username;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setProfilePicture(String profilePicture) {


        if (profilePicture == null || profilePicture.isEmpty()) {
            return;
        }
        // Check if the file extension is valid
        String extension = profilePicture.substring(profilePicture.lastIndexOf('.') + 1).toUpperCase();
        try {
            AcceptedImageFormats.valueOf(extension);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Profile picture format not accepted");
        }
        if (!profilePicture.startsWith("http://") && !profilePicture.startsWith("https://")) {
            throw new ValidationException("Profile picture must start with http:// or https://");
        }

        this.profilePicture = profilePicture;
    }

    public void addOrganization(Organization org) {
        this.organizations.add(org);
    }
    public void updateUser(User userDetails) {

        this.email = userDetails.getEmail();
        this.firstName = userDetails.getFirstName();
        this.lastName = userDetails.getLastName();
        this.username = userDetails.getUsername();
        this.profilePicture = userDetails.getProfilePicture();
        this.isAdmin = userDetails.isAdmin();
        this.address = userDetails.getAddress();
        this.organizations = userDetails.getOrganizations();

    }
}
