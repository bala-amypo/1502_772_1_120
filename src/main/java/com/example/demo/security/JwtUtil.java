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
    private final long EXPIRATION_MILLIS = 1000 * 60 * 60;

    // =====================================================
    // TOKEN GENERATION
    // =====================================================
    public String generateToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername());
    }

    public String generateToken(String username) {
        return createToken(new HashMap<>(), username);
    }

    public String generateToken(Map<String, Object> claims, String username) {
        return createToken(claims, username);
    }

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
    // CLAIM HELPERS (REQUIRED BY TESTS)
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

    public String extractUsername(String token) {
        return getUsername(token);
    }

    public long getExpirationMillis() {
        return EXPIRATION_MILLIS;
    }

    // =====================================================
    // TOKEN VALIDATION (🔥 BOTH OVERLOADS REQUIRED)
    // =====================================================
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    // 🔥 REQUIRED BY TESTS
    public boolean isTokenValid(String token, String username) {
        return getUsername(token).equals(username)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
