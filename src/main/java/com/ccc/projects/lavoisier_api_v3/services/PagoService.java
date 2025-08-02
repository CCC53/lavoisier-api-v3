package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreatePagoRecord;
import com.ccc.projects.lavoisier_api_v3.models.Cita;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Pago;
import com.ccc.projects.lavoisier_api_v3.repositories.CitaRepository;
import com.ccc.projects.lavoisier_api_v3.repositories.PagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;
    private final CitaRepository citaRepository;

    public ListResponse<Pago> findAll(Pageable pageable) {
        Page<Pago> page = pagoRepository.findAll(pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public Pago findOne(UUID id) {
        Optional<Pago> pagOpt = pagoRepository.findById(id);
        if (pagOpt.isEmpty()) {
            throw new NoSuchElementException("Pago not found");
        }
        return pagOpt.get();
    }

    public Pago findOneByCita(UUID citaId) {
        Cita cita = citaRepository.findById(citaId).orElseThrow(() -> new NoSuchElementException("Cita not found"));
        return pagoRepository.findByCita(cita).orElseThrow(() -> new NoSuchElementException("No payment found for this cita"));
    }

    public Pago create(CreatePagoRecord data) {
        Pago pagoDB = new Pago();
        Cita cita = citaRepository.findById(data.citaId()).orElse(null);
        Double cambio = data.cantidadRecibida() - data.monto();
        pagoDB.setMonto(data.monto());
        pagoDB.setCantidadRecibida(data.cantidadRecibida());
        pagoDB.setMetodoPago(data.metodoPago());
        pagoDB.setTipoPago(data.tipoPago());
        pagoDB.setCambio(cambio);
        pagoDB.setCita(cita);
        return pagoRepository.save(pagoDB);
    }
}
