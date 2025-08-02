package com.ccc.projects.lavoisier_api_v3.models;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.ccc.projects.lavoisier_api_v3.dto.MetodosPago;
import com.ccc.projects.lavoisier_api_v3.dto.TipoPago;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity @Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private Double monto;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "metodo_pago")
    private MetodosPago metodoPago;

    @Convert(converter = TipoPagoConverter.class)
    @Column(nullable = false, name = "tipo_pago")
    private TipoPago tipoPago;
    
    @Column(nullable = false, name = "cantidad_recibida")
    private Double cantidadRecibida;

    @Column(nullable = false)
    private Double cambio;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cita_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cita cita;
}
