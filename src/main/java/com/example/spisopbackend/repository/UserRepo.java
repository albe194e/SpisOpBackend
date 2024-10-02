package com.example.spisopbackend.repository;

import com.example.spisopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    List<User> findByIdIsIn(Set<String> id);
}
