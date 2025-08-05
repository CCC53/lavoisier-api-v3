package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdateHistorialRecord;
import com.ccc.projects.lavoisier_api_v3.models.HistorialClinico;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Paciente;
import com.ccc.projects.lavoisier_api_v3.repositories.HistorialClinicoRepository;
import com.ccc.projects.lavoisier_api_v3.repositories.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorialClinicoService {
    private final HistorialClinicoRepository historialClinicoRepository;
    private final PacienteRepository pacienteRepository;

    public ListResponse<HistorialClinico> findAll(Pageable pageable) {
        Page<HistorialClinico> page = historialClinicoRepository.findAll(pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public HistorialClinico findOne(UUID id) {
        Optional<HistorialClinico> hisOpt = historialClinicoRepository.findById(id);
        if (hisOpt.isEmpty()) {
            throw new NoSuchElementException("HistorialClinico not found");
        }
        return hisOpt.get();
    }

    public HistorialClinico create(CreateOrUpdateHistorialRecord data) {
        HistorialClinico historialClinico = new HistorialClinico();
        Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);

        historialClinico.setEnfermedadesCardiovasculares(data.enfermedadesCardiovasculares());
        historialClinico.setEnfermedadesPulmonares(data.enfermedadesPulmonares());
        historialClinico.setEnfermedadesMetabolicas(data.enfermedadesMetabolicas());
        historialClinico.setTabaquismo(data.tabaquismo());
        historialClinico.setAlcoholismo(data.alcoholismo());
        historialClinico.setSedentarismo(data.sedentarismo());
        historialClinico.setDrogas(data.drogas());
        historialClinico.setCafe(data.cafe());
        historialClinico.setPaciente(paciente);
        
        return historialClinicoRepository.save(historialClinico);
    }

    public HistorialClinico update(CreateOrUpdateHistorialRecord data, UUID id) {
        Optional<HistorialClinico> hisOpt = historialClinicoRepository.findById(id);
        if (hisOpt.isPresent()) {
            HistorialClinico historialClinico = hisOpt.get();
            Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);

            historialClinico.setEnfermedadesCardiovasculares(data.enfermedadesCardiovasculares());
            historialClinico.setEnfermedadesPulmonares(data.enfermedadesPulmonares());
            historialClinico.setEnfermedadesMetabolicas(data.enfermedadesMetabolicas());
            historialClinico.setTabaquismo(data.tabaquismo());
            historialClinico.setAlcoholismo(data.alcoholismo());
            historialClinico.setSedentarismo(data.sedentarismo());
            historialClinico.setDrogas(data.drogas());
            historialClinico.setCafe(data.cafe());
            historialClinico.setPaciente(paciente);

            return historialClinicoRepository.save(historialClinico);
        }
        return null;
    }
}
