package com.example.backend.repositories;

import com.example.backend.models.Cart;
import com.example.backend.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findCartByUser(User user);

    @NotNull
    Optional<Cart> findById(Integer id);

    void deleteCartByUser(User user);
}
