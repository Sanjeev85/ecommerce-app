package com.example.backend.repositories;

import com.example.backend.models.OrderItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItem, Integer> {
}
