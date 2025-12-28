package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserAccountRepository repo,
                          PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserAccount user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.get("email"),
                        req.get("password"))
        );

        var userDetails = new org.springframework.security.core.userdetails.User(
                req.get("email"),
                "",
                java.util.List.of()
        );

        String token = jwtUtil.generateToken(userDetails);

        return Map.of("token", token);
    }
}
