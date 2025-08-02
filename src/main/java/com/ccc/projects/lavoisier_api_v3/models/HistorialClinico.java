package com.ccc.projects.lavoisier_api_v3.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.EnfermedadesResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity @Table(name = "historial_clinico")
public class HistorialClinico {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "enf_cardiovasculares")
    private EnfermedadesResponse enfermedadesCardiovasculares;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "enf_pulmonares")
    private EnfermedadesResponse enfermedadesPulmonares;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "enf_metabolicas")
    private EnfermedadesResponse enfermedadesMetabolicas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse tabaquismo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse alcoholismo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse sedentarismo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse drogas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnfermedadesResponse cafe;

    @Column(nullable = false)
    private String alimentacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonIgnore
    private Paciente paciente;
}
