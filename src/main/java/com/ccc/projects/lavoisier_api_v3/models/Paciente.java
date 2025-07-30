package com.ccc.projects.lavoisier_api_v3.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.ValidGenres;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @Table(name = "pacientes")
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue @UuidGenerator
    UUID id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Nacimiento is required")
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate nacimiento;

    @NotNull(message = "Genre is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValidGenres sexo;

    @NotBlank(message = "Phone is required")
    @Column(nullable = false)
    private String telefono;

    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paciente")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private List<Cita> citas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paciente")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private List<Antropometria> antropometricos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paciente")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private List<Laboratorial> laboratoriales;
}
