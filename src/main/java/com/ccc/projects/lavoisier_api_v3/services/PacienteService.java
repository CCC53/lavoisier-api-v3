package com.ccc.projects.lavoisier_api_v3.services;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ccc.projects.lavoisier_api_v3.dto.CreateOrUpdatePacienteRecord;
import com.ccc.projects.lavoisier_api_v3.models.ListResponse;
import com.ccc.projects.lavoisier_api_v3.models.Paciente;
import com.ccc.projects.lavoisier_api_v3.repositories.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repository;

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors()).map(pd -> pd.getName())
            .filter(name -> src.getPropertyValue(name) == null).toArray(String[]::new);
    }

    public ListResponse<Paciente> findAll(Pageable pageable) {
        Page<Paciente> page = repository.findAll(pageable);
        return new ListResponse<>(page.getContent(), page.getTotalElements());
    }

    public Paciente findOne(UUID id) {
        Optional<Paciente> pacienteOpt = repository.findById(id);
        if (pacienteOpt.isEmpty()) {
            throw new NoSuchElementException("Paciente not found");
        }
        return pacienteOpt.get();
    }

    public Paciente createOne(CreateOrUpdatePacienteRecord data) {
        Paciente paciente = new Paciente();
        paciente.setNombre(data.nombre());
        paciente.setNacimiento(data.nacimiento().toLocalDate());
        paciente.setSexo(data.sexo());
        paciente.setTelefono(data.telefono());
        paciente.setEmail(data.email());
        return repository.save(paciente);
    }

    public Paciente updateOne(UUID id, CreateOrUpdatePacienteRecord data) {
        return repository.findById(id).map(existing -> {
            BeanUtils.copyProperties(data, existing, getNullPropertyNames(data));
            return repository.save(existing);
        }).orElse(null);
    }

    public boolean deleteOne(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
