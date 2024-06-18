package com.Clinica_Api.service;

import com.Clinica_Api.model.Paciente;
import com.Clinica_Api.repository.CitaRepository;
import com.Clinica_Api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        // Primero elimina todas las citas asociadas a los pacientes
        List<Paciente> pacientes = pacienteRepository.findAll();
        for (Paciente paciente : pacientes) {
            citaRepository.deleteByPacienteId(paciente.getId());
        }

        // Luego elimina todos los pacientes
        pacienteRepository.deleteAll();
    }

    public List<Paciente> findByNombreContaining(String nombre) {
        return pacienteRepository.findByNombreContaining(nombre);
    }
}

