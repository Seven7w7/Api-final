package com.Clinica_Api.controller;

import com.Clinica_Api.model.Paciente;
import com.Clinica_Api.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(savedPaciente);
    }

    @PostMapping(consumes = "text/plain")
    public ResponseEntity<Paciente> createPacienteFromText(@RequestBody String nombre) {
        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        Paciente savedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(savedPaciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (!pacienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paciente.setId(id);
        Paciente updatedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (!pacienteService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllPacientes() {
        pacienteService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<List<Paciente>> findPacientesByNombre(@RequestParam String nombre) {
        List<Paciente> pacientes = pacienteService.findByNombreContaining(nombre);
        return ResponseEntity.ok(pacientes);
    }
}
