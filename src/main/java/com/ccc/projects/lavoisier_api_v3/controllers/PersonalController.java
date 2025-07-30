package com.ccc.projects.lavoisier_api_v3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.projects.lavoisier_api_v3.models.Personal;
import com.ccc.projects.lavoisier_api_v3.security.GetClaims;
import com.ccc.projects.lavoisier_api_v3.services.PersonalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personal")
public class PersonalController {
    private final PersonalService service;
    private final GetClaims getClaims;

    @GetMapping
    @PreAuthorize("hasAuthority('N')")
    public ResponseEntity<?> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        UUID authenticatedUserId = getClaims.getClaimIdUser();
        return ResponseEntity.status(HttpStatus.OK).body(service.list(authenticatedUserId, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('N')")
    public ResponseEntity<?> findOne(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("data", service.findOne(id)));
    }

    @GetMapping("/me")
    public ResponseEntity<?> profile() {
        UUID authenticatedUserId = getClaims.getClaimIdUser();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("data", service.findOne(authenticatedUserId)));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody Personal data) {
        UUID authenticatedUserId = getClaims.getClaimIdUser();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("data", service.updateOne(authenticatedUserId, data)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('N')")
    public ResponseEntity<?> deleteOne(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("removed", service.deleteOne(id)));
    }
    
}
