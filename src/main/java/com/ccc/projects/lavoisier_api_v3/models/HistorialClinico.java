package com.ccc.projects.lavoisier_api_v3.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.EnfermedadesResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity @Table(name = "historial-clinico")
public class HistorialClinico {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotNull(message = "enfermedadesCardiovasculares is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse enfermedadesCardiovasculares;

    @NotNull(message = "enfermedadesPulmonares is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse enfermedadesPulmonares;

    @NotNull(message = "enfermedadesMetabolicas is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse enfermedadesMetabolicas;

    @NotNull(message = "tabaquismo is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse tabaquismo;

    @NotNull(message = "alcoholismo is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse alcoholismo;

    @NotNull(message = "sedentarismo is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse sedentarismo;

    @NotNull(message = "drogas is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse drogas;

    @NotNull(message = "cafe is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse cafe;

    @NotBlank(message = "alimentacion is required")
    @Column(nullable = false)
    private String alimentacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;
}
