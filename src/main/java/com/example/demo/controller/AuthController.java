package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto dto) {
        return new AuthResponseDto("dummy-jwt-token");
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthRequestDto dto) {}
}
