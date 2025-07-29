package com.ccc.projects.lavoisier_api_v3.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class GetClaims {
    public UUID getClaimIdUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Authentication is not available. User may not be authenticated.");
        }
        Claims claims = (Claims) authentication.getDetails();
        if (claims == null) {
            throw new IllegalStateException("JWT claims are not available in authentication details.");
        }
        String id = claims.get("id", String.class);
        if (id == null) {
            throw new IllegalStateException("User ID not found in JWT claims.");
        }

        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid user ID format in JWT claims: " + id);
        }
    }
}
