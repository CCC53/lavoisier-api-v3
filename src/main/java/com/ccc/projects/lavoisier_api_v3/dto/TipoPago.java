package com.ccc.projects.lavoisier_api_v3.dto;

public enum TipoPago {
    PRIMERO(1),
    POSTERIOR(2);

    private final int value;

    TipoPago(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TipoPago fromValue(int value) {
        for (TipoPago tipo : TipoPago.values()) {
            if (tipo.value == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoPago: " + value);
    }
}
