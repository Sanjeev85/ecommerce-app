package com.example.backend.services;

import com.example.backend.models.Category;
import com.example.backend.repositories.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> listCategories() {
        return categoryRepo.findAll();
    }

    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepo.findById(categoryId);
    }

    public void updateCategory(Integer categoryId, Category newCategory) {
        var category = categoryRepo.findById(categoryId).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setProducts(newCategory.getProducts());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepo.save(category);
    }

}
