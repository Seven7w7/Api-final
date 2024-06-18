package com.Clinica_Api.repository;

import com.Clinica_Api.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    void deleteByPacienteId(Long id);
}
