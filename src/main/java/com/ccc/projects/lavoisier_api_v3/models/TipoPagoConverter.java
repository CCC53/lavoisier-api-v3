package com.ccc.projects.lavoisier_api_v3.models;

import com.ccc.projects.lavoisier_api_v3.dto.TipoPago;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TipoPagoConverter implements AttributeConverter<TipoPago, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoPago tipoPago) {
        return tipoPago != null ? tipoPago.getValue() : null;
    }

    @Override
    public TipoPago convertToEntityAttribute(Integer dbData) {
        return dbData != null ? TipoPago.fromValue(dbData) : null;
    }
} 