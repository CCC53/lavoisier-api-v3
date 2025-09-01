package com.ccc.projects.lavoisier_api_v3.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateLaboratorialRecord(
    @NotNull(message = "Fecha is required") OffsetDateTime fecha,
    @NotNull(message = "glucosa is required") Double glucosa,
    @NotNull(message = "insulina is required") Double insulina,
    @NotNull(message = "trigliceridos is required") Double trigliceridos,
    @NotNull(message = "colesterolTotal is required") Double colesterolTotal,
    @NotNull(message = "hdl is required") Double hdl,
    @NotNull(message = "ldl is required") Double ldl,
    @NotNull(message = "PacienteId is required") UUID pacienteId 
) {

}
