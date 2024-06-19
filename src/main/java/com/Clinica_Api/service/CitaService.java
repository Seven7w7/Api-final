package com.Clinica_Api.service;


import com.Clinica_Api.model.Cita;
import com.Clinica_Api.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    public Cita save(Cita cita) {
        // Verificar que el paciente y el medico existan antes de guardar la cita
        pacienteService.findById(cita.getPaciente().getId()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        medicoService.findById(cita.getMedico().getId()).orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        return citaRepository.save(cita);
    }

    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    public void deleteById(Long id) {
        citaRepository.deleteById(id);
    }

    public void deleteAll() {
        citaRepository.deleteAll();
    }
}


