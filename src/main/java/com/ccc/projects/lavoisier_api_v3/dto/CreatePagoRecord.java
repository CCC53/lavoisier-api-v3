package com.ccc.projects.lavoisier_api_v3.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreatePagoRecord(
    @NotNull(message = "monto is required") Double monto,
    @NotNull(message = "metodoPago is required") MetodosPago metodoPago,
    @NotNull(message = "tipoPago is required") TipoPago tipoPago,
    @NotNull(message = "cantidadRecibida is required") Double cantidadRecibida,
    @NotNull(message = "CitaId is required") UUID citaId
) {

}
