package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Personal;
import com.ccc.projects.lavoisier_api_v3.repositories.PersonalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalService {
    private final PersonalRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Personal findOne(UUID id) {
        Optional<Personal> personalOptional = repository.findById(id);
        if (personalOptional.isEmpty()) {
            throw new NoSuchElementException("Personal not found");
        }
        return personalOptional.get();
    }

    public ListResponse<Personal> list(UUID id, Pageable pageable) {
        Page<Personal> page = repository.findByIdNot(id, pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public Personal createOne(Personal data) {
        if (repository.existsByEmail(data.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        return repository.save(data);
    }

    public Personal updateOne(UUID id, Personal data) {
        Optional<Personal> perOptional = repository.findById(id);
        if (perOptional.isPresent()) {
            Personal personal = perOptional.get();
            if (data.getNombre() != null) {
                personal.setNombre(data.getNombre());
            }
            if (data.getEmail() != null) {
                personal.setEmail(data.getEmail());
            }
            if (data.getTelefono() != null) {
                personal.setTelefono(data.getTelefono());
            }
            if (data.getRol() != null) {
                personal.setRol(data.getRol());
            }
            return repository.save(personal);
        }
        return null;
    }

    public boolean deleteOne(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Personal findByEmail(String email) {
        Optional<Personal> perOptional = repository.findByEmail(email);
        if (perOptional.isEmpty()) {
            throw new NoSuchElementException("Personal not found");
        }
        return perOptional.get();
    }
}
