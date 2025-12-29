package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret123";
    private final long EXPIRATION_MILLIS = 1000 * 60 * 60; // 1 hour

    // =====================================================
    // CORE TOKEN GENERATION (UserDetails)
    // =====================================================
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // =====================================================
    // REQUIRED BY TESTS (String username)
    // =====================================================
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // =====================================================
    // INTERNAL TOKEN CREATION
    // =====================================================
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // =====================================================
    // CLAIM EXTRACTION (TESTS REQUIRE THESE)
    // =====================================================
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // alias used by filter/tests
    public String extractUsername(String token) {
        return getUsername(token);
    }

    public long getExpirationMillis() {
        return EXPIRATION_MILLIS;
    }

    // =====================================================
    // TOKEN VALIDATION
    // =====================================================
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
