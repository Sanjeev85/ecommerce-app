package com.example.backend.services

import com.example.backend.models.Order
import com.example.backend.models.User
import com.example.backend.repositories.OrderItemsRepo
import com.example.backend.repositories.OrderRepo
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
@Transactional
open class OrderService {
    @Autowired
    private lateinit var cartService: CartService

    @Autowired
    private lateinit var orderRepo: OrderRepo

    @Autowired
    private lateinit var orderItemsRepo: OrderItemsRepo

    fun listOrders(user: User): List<Order> {
        return orderRepo.findAllByUserOrderByCreatedDateDesc(user);
    }

    fun getOrder(orderId: Int): Optional<Order> {
        return orderRepo.findById(orderId)
    }
}