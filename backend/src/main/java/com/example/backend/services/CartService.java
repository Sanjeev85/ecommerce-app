package com.example.backend.services;


import com.example.backend.dto.cart.AddToCartDto;
import com.example.backend.dto.cart.CartDto;
import com.example.backend.dto.cart.CartItemDto;
import com.example.backend.models.Cart;
import com.example.backend.models.Product;
import com.example.backend.models.User;
import com.example.backend.repositories.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class CartService {

    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        var cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepo.save(cart);
    }

    public CartDto listCartItems(User user) {
        var cartList = cartRepo.findCartByUser(user);
        var cartItems = new ArrayList<CartItemDto>();
        for (Cart cart : cartList) {
            var cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }
        return new CartDto(cartItems, totalCost);
    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
        var cart = cartRepo.getById(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepo.save(cart);
    }

    public void deleteCartItem(int id) {
        cartRepo.deleteById(id);
    }

    public void deleteAllCartItems(int userId) {
        cartRepo.deleteAll();
    }

    public void deleteUserCartItems(User user) {
        cartRepo.deleteCartByUser(user);
    }

}
