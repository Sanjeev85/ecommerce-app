package com.example.backend.controller

import com.example.backend.exception.OrderNotFoundException
import com.example.backend.models.Order
import com.example.backend.services.OrderService
import com.example.backend.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/orders")
class OrderController {
    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/add")
    fun placeOrder(@RequestParam("token") token: String): ResponseEntity<List<Order>> {
        authService.authenticate(token)
        val user = authService.getUser(token)
        val orderDtoList = orderService.listOrders(user)
        return ResponseEntity(orderDtoList, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getOrderById(
        @PathVariable("id") id: Int,
        @RequestParam("token") token: String
    ): ResponseEntity<out Any?> {
        authService.authenticate(token)
        return try {
            val order = orderService.getOrder(id)
            ResponseEntity(order, HttpStatus.OK)
        } catch (e: OrderNotFoundException) {
            ResponseEntity(e.message, HttpStatus.OK)
        }
    }

}