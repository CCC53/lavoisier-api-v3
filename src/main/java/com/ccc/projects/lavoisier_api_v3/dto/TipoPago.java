package com.ccc.projects.lavoisier_api_v3.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoPago {
    PRIMERO(1),
    POSTERIOR(2);

    private final int value;

    TipoPago(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static TipoPago fromValue(int value) {
        for (TipoPago tipo : TipoPago.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoPago: " + value);
    }
}
