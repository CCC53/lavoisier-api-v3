package com.ccc.projects.lavoisier_api_v3.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccc.projects.lavoisier_api_v3.models.HistorialClinico;

public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, UUID> {

}
