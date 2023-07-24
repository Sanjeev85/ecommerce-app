package com.example.backend.services;


import com.example.backend.dto.product.ProductDto;
import com.example.backend.models.Category;
import com.example.backend.models.Product;
import com.example.backend.repositories.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductDto> listProducts() {
        List<Product> products = productRepo.findAll();
        var productDtos = new ArrayList<ProductDto>();
        for (Product product : products) {
            var productDto = new ProductDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public void addProduct(ProductDto productDto, Category category) {
        var product = new Product(productDto, category);
        productRepo.save(product);
    }

    public void updateProduct(Integer productId, ProductDto productDto, Category category) {
        var product = new Product(productDto, category);
        product.setId(productId);
        productRepo.save(product);
    }

    public Product getProductById(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        return product.get();
    }

}
