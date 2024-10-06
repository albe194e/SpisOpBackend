package com.example.spisopbackend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Community")
public class Community extends Organization{

    public Community(String name, User createdBy) {
        this.setName(name);
        this.createdBy = createdBy;
        this.users = Set.of();
    }
    public Community() {
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
