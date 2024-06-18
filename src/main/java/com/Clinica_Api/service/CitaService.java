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

    public Cita save(Cita cita) {
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

