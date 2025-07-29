package com.ccc.projects.lavoisier_api_v3.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.projects.lavoisier_api_v3.dto.LoginRecord;
import com.ccc.projects.lavoisier_api_v3.models.Personal;
import com.ccc.projects.lavoisier_api_v3.security.JWTUtil;
import com.ccc.projects.lavoisier_api_v3.services.PersonalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final PersonalService service;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Personal data) {
        Personal personalDB = service.createOne(data);
        String token = jwtUtil.generateJWT(personalDB.getEmail(), personalDB.getRol().toString(), personalDB.getId().toString());
        return ResponseEntity.ok(Map.of("token", token, "personal", personalDB));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRecord data) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
            Personal personal = service.findByEmail(data.email());
            String token = jwtUtil.generateJWT(data.email(), personal.getRol().toString(), personal.getId().toString());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }
}
