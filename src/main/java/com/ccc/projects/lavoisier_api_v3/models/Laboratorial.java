package com.ccc.projects.lavoisier_api_v3.models;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @Table(name = "laboratorial")
@NoArgsConstructor
public class Laboratorial {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotNull(message = "Fecha is required")
    @Column(nullable = false)
    private Date fecha;

    @NotNull(message = "glucosa is required")
    @Column(nullable = false)
    private Double glucosa;

    @NotNull(message = "insulina is required")
    @Column(nullable = false)
    private Double insulina;

    @NotNull(message = "trigliceridos is required")
    @Column(nullable = false)
    private Double trigliceridos;

    @NotNull(message = "colesterolTotal is required")
    @Column(nullable = false)
    private Double colesterolTotal;

    @NotNull(message = "hdl is required")
    @Column(nullable = false)
    private Double hdl;

    @NotNull(message = "ldl is required")
    @Column(nullable = false)
    private Double ldl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;
}
