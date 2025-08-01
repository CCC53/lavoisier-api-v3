package com.ccc.projects.lavoisier_api_v3.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdateLaboratorialRecord;
import com.ccc.projects.lavoisier_api_v3.models.Laboratorial;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Paciente;
import com.ccc.projects.lavoisier_api_v3.repositories.LaboratorialRepository;
import com.ccc.projects.lavoisier_api_v3.repositories.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaboratorialService {
    private final LaboratorialRepository laboratorialRepository;
    private final PacienteRepository pacienteRepository;

    public ListResponse<Laboratorial> findAll(Pageable pageable) {
        Page<Laboratorial> page = laboratorialRepository.findAll(pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public Laboratorial findOne(UUID id) {
        Optional<Laboratorial> labOpt = laboratorialRepository.findById(id);
        if (labOpt.isEmpty()) {
            throw new NoSuchElementException("Laboratorial not found");
        }
        return labOpt.get();
    }

    public Laboratorial create(CreateOrUpdateLaboratorialRecord data) {
        Laboratorial laboratorialDB = new Laboratorial();
        Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);

        laboratorialDB.setFecha(data.fecha());
        laboratorialDB.setGlucosa(data.glucosa());
        laboratorialDB.setInsulina(data.insulina());
        laboratorialDB.setTrigliceridos(data.trigliceridos());
        laboratorialDB.setColesterolTotal(data.colesterolTotal());
        laboratorialDB.setHdl(data.hdl());
        laboratorialDB.setLdl(data.ldl());
        laboratorialDB.setPaciente(paciente);

        return laboratorialRepository.save(laboratorialDB);
    }

    public Laboratorial update(CreateOrUpdateLaboratorialRecord data, UUID id) {
        Optional<Laboratorial> labOpt = laboratorialRepository.findById(id);
        if (labOpt.isPresent()) {
            Laboratorial laboratorialDB = labOpt.get();
            Paciente paciente = pacienteRepository.findById(data.pacienteId()).orElse(null);

            laboratorialDB.setFecha(data.fecha());
            laboratorialDB.setGlucosa(data.glucosa());
            laboratorialDB.setInsulina(data.insulina());
            laboratorialDB.setTrigliceridos(data.trigliceridos());
            laboratorialDB.setColesterolTotal(data.colesterolTotal());
            laboratorialDB.setHdl(data.hdl());
            laboratorialDB.setLdl(data.ldl());
            laboratorialDB.setPaciente(paciente);

            return laboratorialRepository.save(laboratorialDB);
        }
        return null;
    }

}
