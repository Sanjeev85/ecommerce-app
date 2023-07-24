package com.example.backend.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String message) {
        super(message);
    }
}
