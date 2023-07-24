package com.example.backend.repositories;

import com.example.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);
}
