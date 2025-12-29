package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final long EXPIRATION = 60 * 60 * 1000;
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 🔹 USED BY TESTS
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails.getUsername());
    }

    // 🔹 USED INTERNALLY
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🔹 FILTER SUPPORT
    public String extractUsername(String token) {
        return getUsername(token);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public long getExpirationMillis() {
        return EXPIRATION;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return getUsername(token).equals(userDetails.getUsername())
                && !getClaims(token).getExpiration().before(new Date());
    }
}
