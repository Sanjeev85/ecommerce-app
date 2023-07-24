package com.example.backend.models;

import com.example.backend.dto.product.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String imageUrl;
    private double price;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categiry_id", nullable = false)
    Category category;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Cart> carts;

    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.imageUrl = productDto.getImageURL();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
    }

}














