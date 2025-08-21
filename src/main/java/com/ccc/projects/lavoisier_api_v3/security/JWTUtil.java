package com.ccc.projects.lavoisier_api_v3.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    @Value("${jwt.seed}")
    private String seed;

    @Value("${jwt.expiration}")
    private Long expiresIn;

    public String generateJWT(String email, String role, String id) {
        return Jwts.builder().subject(email).claim("role", role).claim("id", id).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiresIn))
            .signWith(Keys.hmacShaKeyFor(seed.getBytes()), Jwts.SIG.HS256).compact();
    }

    public String validateJWTAndGetEmail(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(seed.getBytes())).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(seed.getBytes())).build().parseSignedClaims(token).getPayload();
    }
}
