package com.ccc.projects.lavoisier_api_v3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdateCitaRecord;
import com.ccc.projects.lavoisier_api_v3.services.CitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cita")
public class CitaController {
    private final CitaService service;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable UUID id) {
        return ResponseEntity.ok(Map.of("data", service.findOne(id)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateOrUpdateCitaRecord data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", service.create(data)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CreateOrUpdateCitaRecord entity, @PathVariable UUID id) {
        return ResponseEntity.ok(Map.of("data", service.update(entity, id)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable UUID id) {
        return ResponseEntity.ok(Map.of("removed", service.deleteOne(id)));
    }
    
}
