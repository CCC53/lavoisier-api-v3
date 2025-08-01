package com.ccc.projects.lavoisier_api_v3.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateAntrometriaRecord(
    @NotNull(message = "Fecha is required") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDate fecha,
    @NotNull(message = "Peso is required") Double peso,
    @NotNull(message = "Talla is required") Double talla,
    @NotNull(message = "IMC is required") Double imc,
    @NotNull(message = "Cintura is required") Double cintura,
    @NotNull(message = "cBrazo is required") Double cBrazo,
    @NotNull(message = "pTriceps is required") Double pTriceps,
    @NotNull(message = "pAbdominal is required") Double pAbdominal,
    @NotNull(message = "porcentajeGrasa is required") String porcentajeGrasa,
    @NotNull(message = "PacienteId is required") UUID pacienteId
) {

}
