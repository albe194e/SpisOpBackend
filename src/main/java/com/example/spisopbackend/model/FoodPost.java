package com.example.spisopbackend.model;

import com.example.spisopbackend.Exceptions.ValidationException;
import lombok.Data;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "FoodPost")
public class FoodPost {

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String image;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime lastUpdated;

    @ManyToOne
    @JoinColumn(name = "authorUserId")
    private User authorUser;

    @ManyToOne
    @JoinColumn(name = "authorCompanyId")
    private Company authorCompany;

    @ManyToOne
    @JoinColumn(name = "communityId")
    private Community community;

    // FoodPost-Allergy Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "FoodPost_Allergy",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "allergyId")
    )
    private Set<Allergy> allergies;

    public FoodPost(String title, String description, BigDecimal price) {
        this.setTitle(title);
        this.setDescription(description);
        this.setPrice(price);
        this.setCreatedAt();
    }
    public FoodPost() {
    }

    public void addAllergy(Allergy allergy) {
        this.allergies.add(allergy);
    }

    public void updateFoodPost(FoodPost foodPostDetails) {

        this.id = foodPostDetails.getId();
        this.image = foodPostDetails.getImage();
        this.title = foodPostDetails.getTitle();
        this.description = foodPostDetails.getDescription();
        this.price = foodPostDetails.getPrice();
        this.authorUser = foodPostDetails.getAuthorUser();
        this.authorCompany = foodPostDetails.getAuthorCompany();
        this.community = foodPostDetails.getCommunity();
        this.allergies = foodPostDetails.getAllergies();
        this.setLastUpdated();
    }

    public void setTitle(String title) {

        if (title == null || title.isEmpty()) {
            throw new ValidationException("Title cannot be empty");
        }

        // This will count emojis as 1 character, as they are interpreted as 2
        int codePointCount = title.codePointCount(0, title.length());
        if (codePointCount > 50) {
            throw new ValidationException("Title cannot be longer than 50 characters");
        }

        if (title.trim().isEmpty()) {
            throw new ValidationException("Title cannot be empty");
        }
        this.title = title;
    }

    public void setDescription(String description) {

        if (description.trim().isEmpty()) {
            description = "";
        }
        // Storing emojis in db counts as 1 character
        int codePointCount = description.codePointCount(0, description.length());
        System.out.println(codePointCount);
        if (codePointCount > 255) {
            throw new ValidationException("Title cannot be longer than 255 characters");
        }

        this.description = description;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
