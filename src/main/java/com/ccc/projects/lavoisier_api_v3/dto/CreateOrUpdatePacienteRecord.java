package com.ccc.projects.lavoisier_api_v3.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrUpdatePacienteRecord(
    @NotBlank(message = "Name is required") String nombre,
    @NotNull(message = "Nacimiento is required") OffsetDateTime nacimiento,
    @NotNull(message = "Genre is required") ValidGenres sexo,
    @NotBlank(message = "Phone is required") String telefono,
    @NotBlank(message = "Email is required") String email
) {

}
