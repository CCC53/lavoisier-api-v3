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
@Entity @Table(name = "antropometria")
@NoArgsConstructor
public class Antropometria {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotNull(message = "Fecha is required")
    @Column(nullable = false)
    private Date fecha;

    @NotNull(message = "Peso is required")
    @Column(nullable = false)
    private Double peso;

    @NotNull(message = "Talla is required")
    @Column(nullable = false)
    private Double talla;

    @NotNull(message = "IMC is required")
    @Column(nullable = false)
    private Double imc;

    @NotNull(message = "Cintura is required")
    @Column(nullable = false)
    private Double cintura;

    @NotNull(message = "cBrazo is required")
    @Column(nullable = false)
    private Double cBrazo;

    @NotNull(message = "pTriceps is required")
    @Column(nullable = false)
    private Double pTriceps;

    @NotNull(message = "pAbdominal is required")
    @Column(nullable = false)
    private Double pAbdominal;

    @NotNull(message = "porcentajeGrasa is required")
    @Column(nullable = false)
    private Double porcentajeGrasa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pacienteId", nullable = false)
    private Paciente paciente;
}
