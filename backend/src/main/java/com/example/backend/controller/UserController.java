package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.user.SignInDto;
import com.example.backend.dto.user.SignInResponseDto;
import com.example.backend.dto.user.SignupDto;
import com.example.backend.models.User;
import com.example.backend.repositories.UserRepo;
import com.example.backend.services.AuthService;
import com.example.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepo userRepo;
    private final AuthService authService;
    private final UserService userService;

    public UserController(UserRepo userRepo, AuthService authService, UserService userService) {
        this.userRepo = userRepo;
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAllUsers(@RequestParam("token") String token) throws Exception {
        authService.authenticate(token);
        return userRepo.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto) throws Exception {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws Exception {
        return userService.signIn(signInDto);
    }

}




















