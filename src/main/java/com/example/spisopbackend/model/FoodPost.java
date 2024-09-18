package com.example.spisopbackend.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "FoodPost")
public class FoodPost {

    @Id
    @Column(length = 36)
    private String id;

    @Column(length = 255)
    private String image;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

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
}
