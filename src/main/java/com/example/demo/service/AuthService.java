package com.example.demo.service;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto login(AuthRequestDto request);

    AuthResponseDto register(RegisterRequestDto request);
}
