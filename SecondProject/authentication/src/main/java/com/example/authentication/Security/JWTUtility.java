package com.example.authentication.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtility {

    // Static key for HS256 algorithm
    private final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Time
    private final long accessTokenValidity = 900000; // 15 min in milliseconds
    private final long refreshTokenValidity = 604800000; // 7 days in milliseconds

    // For Access Token
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(key)
                .compact();
    }

    // For Refresh Token
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(key)
                .compact();
    }

    // Extract Claims from Token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }

    // Extract Username from Token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Check if Token is Expired
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractEmail(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

}
