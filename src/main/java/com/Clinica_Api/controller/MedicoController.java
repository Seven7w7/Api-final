package com.Clinica_Api.controller;

import com.Clinica_Api.model.Medico;
import com.Clinica_Api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico savedMedico = medicoService.save(medico);
        return ResponseEntity.ok(savedMedico);
    }

    @PostMapping(consumes = "text/plain")
    public ResponseEntity<Medico> createMedicoFromText(@RequestBody String nombre) {
        Medico medico = new Medico();
        medico.setNombre(nombre);
        Medico savedMedico = medicoService.save(medico);
        return ResponseEntity.ok(savedMedico);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.findAll();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Long id) {
        return medicoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico medico) {
        if (!medicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medico.setId(id);
        Medico updatedMedico = medicoService.save(medico);
        return ResponseEntity.ok(updatedMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        if (!medicoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMedicos() {
        medicoService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<List<Medico>> findMedicosByNombre(@RequestParam String nombre) {
        List<Medico> medicos = medicoService.findByNombreContaining(nombre);
        return ResponseEntity.ok(medicos);
    }
}
