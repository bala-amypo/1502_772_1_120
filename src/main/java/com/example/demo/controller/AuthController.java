package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public UserAccount create(@RequestBody UserAccount user) {
        return authService.create(user);
    }

    @GetMapping
    public List<UserAccount> getAll() {
        return authService.getAll();
    }
}
