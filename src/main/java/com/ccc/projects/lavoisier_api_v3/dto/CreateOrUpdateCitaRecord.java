package com.ccc.projects.lavoisier_api_v3.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateCitaRecord(
    @NotBlank(message = "Motivo is required") String motivo,
    @NotNull(message = "Fecha is required") OffsetDateTime fecha,
    @NotBlank(message = "Horario is required") String horario,
    @NotNull(message = "Paciente is required") UUID paciente
) {

}
