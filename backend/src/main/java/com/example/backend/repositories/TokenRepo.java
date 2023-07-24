package com.example.backend.repositories;

import com.example.backend.models.AuthenticationToken;
import com.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);

    AuthenticationToken findTokenByToken(String token);
}
