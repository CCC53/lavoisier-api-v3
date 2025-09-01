package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdateCitaRecord;
import com.ccc.projects.lavoisier_api_v3.models.Cita;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Paciente;
import com.ccc.projects.lavoisier_api_v3.repositories.CitaRepository;
import com.ccc.projects.lavoisier_api_v3.repositories.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitaService {
    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;

    public ListResponse<Cita> findAll(Pageable pageable) {
        Page<Cita> page = citaRepository.findAll(pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public Cita findOne(UUID id) {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isEmpty()) {
            throw new NoSuchElementException("Cita not found");
        }
        return citaOpt.get();
    }

    public Cita create(CreateOrUpdateCitaRecord data) {
        Cita citaDB = new Cita();
        Paciente paciente = pacienteRepository.findById(data.paciente()).orElse(null);
        citaDB.setMotivo(data.motivo());
        citaDB.setFecha(data.fecha().toLocalDate());
        citaDB.setHorario(data.horario());
        citaDB.setPaciente(paciente);
        return citaRepository.save(citaDB);
    }

    public Cita update(CreateOrUpdateCitaRecord data, UUID id) {
        Optional<Cita> citaOpt = citaRepository.findById(id);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            Paciente paciente = pacienteRepository.findById(data.paciente()).orElse(null);
            cita.setMotivo(data.motivo());
            cita.setFecha(data.fecha().toLocalDate());
            cita.setHorario(data.horario());
            cita.setPaciente(paciente);
            return citaRepository.save(cita);
        }
        return null;
    }

    public boolean deleteOne(UUID id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
