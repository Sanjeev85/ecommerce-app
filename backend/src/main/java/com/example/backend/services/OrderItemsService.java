package com.example.backend.services;

import com.example.backend.models.OrderItem;
import com.example.backend.repositories.OrderItemsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderItemsService {
    private final OrderItemsRepo orderItemsRepo;

    public OrderItemsService(OrderItemsRepo orderItemsRepo) {
        this.orderItemsRepo = orderItemsRepo;
    }

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepo.save(orderItem);
    }
}
