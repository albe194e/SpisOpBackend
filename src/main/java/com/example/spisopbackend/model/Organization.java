package com.example.spisopbackend.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Organization {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  protected int id;

  @Column(length = 255)
  protected String name;

  @ManyToOne
  @JoinColumn(name = "addressId", foreignKey = @ForeignKey(name = "fk_organization_address"))
  protected Address address;

  @ManyToOne
  @JoinColumn(name = "createdById", foreignKey = @ForeignKey(name = "fk_organization_created_by"))
  protected User createdBy;

  @ManyToMany(mappedBy = "organizations")
  @JsonBackReference
  protected Set<User> users = new HashSet<>();

  public void setName(String name) {
      // Add validation here
      this.name = name;
  }

  // Constructors
  public Organization() {
  }

  public Organization(int id) {
    this.setId(id);
  }

  public Organization(String name, Address address, User createdBy) {
      this.name = name;
      this.address = address;
      this.createdBy = createdBy;
  }
}
