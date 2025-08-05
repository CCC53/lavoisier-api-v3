package com.ccc.projects.lavoisier_api_v3.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateOrUpdateHistorialRecord(
    @NotNull(message = "enfermedadesCardiovasculares is required") EnfermedadesResponse enfermedadesCardiovasculares,
    @NotNull(message = "enfermedadesPulmonares is required") EnfermedadesResponse enfermedadesPulmonares,
    @NotNull(message = "enfermedadesMetabolicas is required") EnfermedadesResponse enfermedadesMetabolicas,
    @NotNull(message = "tabaquismo is required") EnfermedadesResponse tabaquismo,
    @NotNull(message = "alcoholismo is required") EnfermedadesResponse alcoholismo,
    @NotNull(message = "sedentarismo is required") EnfermedadesResponse sedentarismo,
    @NotNull(message = "drogas is required") EnfermedadesResponse drogas,
    @NotNull(message = "cafe is required") EnfermedadesResponse cafe,
    @NotNull(message = "PacienteId is required") UUID pacienteId
) {

}
