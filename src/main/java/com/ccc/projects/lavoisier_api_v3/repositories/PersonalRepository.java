package com.ccc.projects.lavoisier_api_v3.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccc.projects.lavoisier_api_v3.models.Personal;

public interface PersonalRepository extends JpaRepository<Personal, UUID> {
    boolean existsByEmail(String email);
    Optional<Personal> findByEmail(String email);
}
