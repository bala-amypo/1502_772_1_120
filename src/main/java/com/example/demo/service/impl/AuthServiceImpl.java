package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Map<String, String> login(String email, String password) {

        UserAccount user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        User userDetails = new User(
                user.getEmail(),
                user.getPassword(),
                List.of()
        );

        String token = jwtUtil.generateToken(userDetails);

        return Map.of("token", token);
    }
}
