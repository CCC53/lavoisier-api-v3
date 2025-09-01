package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdateAntrometriaRecord;
import com.ccc.projects.lavoisier_api_v3.models.Antropometria;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Paciente;
import com.ccc.projects.lavoisier_api_v3.repositories.AntropometriaRepository;
import com.ccc.projects.lavoisier_api_v3.repositories.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AntropometriaService {
    private final PacienteRepository pacienteRepository;
    private final AntropometriaRepository antropometriaRepository;

    public ListResponse<Antropometria> findAll(Pageable pageable) {
        Page<Antropometria> data =  antropometriaRepository.findAll(pageable);
        return new ListResponse<>(data.getContent(), data.getTotalElements());
    }

    public Antropometria findOne(UUID id) {
        Optional<Antropometria> antrOpt = antropometriaRepository.findById(id);
        if (antrOpt.isEmpty()) {
            throw new NoSuchElementException("Antropometrico not found");
        }
        return antrOpt.get();
    }

    public Antropometria create(CreateOrUpdateAntrometriaRecord data) {
        Antropometria antropometriaDB = new Antropometria();
        Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);
        antropometriaDB.setFecha(data.fecha().toLocalDate());
        antropometriaDB.setPeso(data.peso());
        antropometriaDB.setTalla(data.talla());
        antropometriaDB.setImc(data.imc());
        antropometriaDB.setCintura(data.cintura());
        antropometriaDB.setCBrazo(data.cBrazo());
        antropometriaDB.setPTriceps(data.pTriceps());
        antropometriaDB.setPAbdominal(data.pAbdominal());
        antropometriaDB.setPorcentajeGrasa(data.porcentajeGrasa());
        antropometriaDB.setPaciente(paciente);
        
        return antropometriaRepository.save(antropometriaDB);
    }

    public Antropometria update(CreateOrUpdateAntrometriaRecord data, UUID id) {
        Optional<Antropometria> antrOpt = antropometriaRepository.findById(id);
        if (antrOpt.isPresent()) {
            Antropometria antropometriaDB = antrOpt.get();
            Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);
            antropometriaDB.setFecha(data.fecha().toLocalDate());
            antropometriaDB.setPeso(data.peso());
            antropometriaDB.setTalla(data.talla());
            antropometriaDB.setImc(data.imc());
            antropometriaDB.setCintura(data.cintura());
            antropometriaDB.setCBrazo(data.cBrazo());
            antropometriaDB.setPTriceps(data.pTriceps());
            antropometriaDB.setPAbdominal(data.pAbdominal());
            antropometriaDB.setPorcentajeGrasa(data.porcentajeGrasa());
            antropometriaDB.setPaciente(paciente);
            return antropometriaRepository.save(antropometriaDB);
        }
        return null;
    }
}
