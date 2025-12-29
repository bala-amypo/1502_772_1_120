package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String username) {
        return "TOKEN";
    }

    public Claims getClaims(String token) {
        return null;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public long getExpirationMillis() {
        return 1000 * 60 * 60;
    }

    public boolean isTokenValid(String token, String username) {
        return getUsername(token).equals(username);
    }

    public boolean validateToken(String token, String username) {
        return isTokenValid(token, username);
    }
}
