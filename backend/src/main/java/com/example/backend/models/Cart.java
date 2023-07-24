package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private int quantity;

    public Cart() {
    }

    public Cart(Product product, int quantity, User user) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.createdDate = new Date();
    }
}
