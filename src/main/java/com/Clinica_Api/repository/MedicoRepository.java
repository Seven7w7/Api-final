package com.Clinica_Api.repository;

import com.Clinica_Api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByNombreContaining(String nombre);
}
