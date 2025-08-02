package com.ccc.projects.lavoisier_api_v3.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.projects.lavoisier_api_v3.dto.CreatePagoRecord;
import com.ccc.projects.lavoisier_api_v3.services.PagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pagos")
@PreAuthorize("hasAuthority('R')")
public class PagoController {
    private final PagoService service;

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable UUID id) {
        return ResponseEntity.ok(Map.of("data", service.findOne(id)));
    }

    @GetMapping("/{citaId}")
    public ResponseEntity<?> findByCita(@PathVariable UUID citaId) {
        return ResponseEntity.ok(Map.of("data", service.findOneByCita(citaId)));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreatePagoRecord data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("data", service.create(data)));
    }
}
