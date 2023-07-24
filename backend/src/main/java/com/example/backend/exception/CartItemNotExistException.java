package com.example.backend.exception;

public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String message) {
        super(message);
    }
}
