package com.ccc.projects.lavoisier_api_v3.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.MetodosPago;
import com.ccc.projects.lavoisier_api_v3.dto.TipoPago;

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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity @Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @NotNull(message = "monto is required")
    @Column(nullable = false)
    private Double monto;

    @NotNull(message = "metodoPago is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MetodosPago metodoPago;

    @NotNull(message = "tipoPago is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPago tipoPago;

    @NotNull(message = "cantidadRecibida is required")
    @Column(nullable = false)
    private Double cantidadRecibida;

    @NotNull(message = "cambio is required")
    @Column(nullable = false)
    private Double cambio;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citaId", nullable = false)
    private Cita cita;
}
