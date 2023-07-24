package com.example.backend.controller;


import com.example.backend.dto.cart.AddToCartDto;
import com.example.backend.dto.cart.CartDto;
import com.example.backend.models.Product;
import com.example.backend.models.User;
import com.example.backend.services.CartService;
import com.example.backend.services.ProductService;
import com.example.backend.services.AuthService;
import com.example.backend.xtraa.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final AuthService authService;

    public CartController(CartService cartService, ProductService productService, AuthService authService) {
        this.cartService = cartService;
        this.productService = productService;
        this.authService = authService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto
            , @RequestParam("token") String token) throws Exception {
        authService.authenticate(token);
        User user = authService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws Exception {
        authService.authenticate(token);
        User user = authService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws Exception {
        authService.authenticate(token);
        User user = authService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user, product);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been update"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemId, @RequestParam("token") String token) throws Exception {
        authService.authenticate(token);
        int userId = authService.getUser(token).getId();
        cartService.deleteCartItem(itemId);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
