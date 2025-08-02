package com.ccc.projects.lavoisier_api_v3.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccc.projects.lavoisier_api_v3.models.Cita;
import com.ccc.projects.lavoisier_api_v3.models.Pago;

public interface PagoRepository extends JpaRepository<Pago, UUID> {
    Optional<Pago> findByCita(Cita cita);
}
