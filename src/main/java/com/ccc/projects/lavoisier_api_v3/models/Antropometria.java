package com.ccc.projects.lavoisier_api_v3.models;

import java.time.LocalDate;
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
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity @Table(name = "antropometria")
@NoArgsConstructor
public class Antropometria {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    
    @Column(nullable = false)
    private Double peso;
    
    @Column(nullable = false)
    private Double talla;
    
    @Column(nullable = false)
    private Double imc;
    
    @Column(nullable = false)
    private Double cintura;

    @Column(name = "c_brazo", nullable = false)
    private Double cBrazo;
    
    @Column(name = "p_triceps", nullable = false)
    private Double pTriceps;

    @Column(name = "p_abdominal", nullable = false)
    private Double pAbdominal;

    @Column(name = "porcentaje_grasa", nullable = false)
    private String porcentajeGrasa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonBackReference
    private Paciente paciente;
}
