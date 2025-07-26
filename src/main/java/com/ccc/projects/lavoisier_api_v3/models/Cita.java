package com.ccc.projects.lavoisier_api_v3.models;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity @Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotBlank(message = "Motivo is required")
    @Column(nullable = false)
    private String motivo;

    @NotNull(message = "Fecha is required")
    @Column(nullable = false)
    private Date fecha;

    @NotBlank(message = "Motivo is required")
    @Column(nullable = false)
    private String horario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;
}
