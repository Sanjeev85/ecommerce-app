package com.example.backend.repositories;

import com.example.backend.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @NotNull
    List<User> findAll();

    User findByEmail(String email);

    User findUserByEmail(String email);
}
