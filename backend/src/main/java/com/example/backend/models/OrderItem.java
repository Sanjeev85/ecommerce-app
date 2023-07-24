package com.example.backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int quantity;
    private double price;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}






























