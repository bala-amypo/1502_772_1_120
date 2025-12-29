package com.example.demo.dto;

public class AuthResponseDto {

    private String message;
    private String role;

    public AuthResponseDto(String message, String role) {
        this.message = message;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }
}
