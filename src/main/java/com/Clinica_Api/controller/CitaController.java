package com.Clinica_Api.controller;


import com.Clinica_Api.model.Cita;
import com.Clinica_Api.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Cita savedCita = citaService.save(cita);
        return ResponseEntity.ok(savedCita);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.findAll();
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return citaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Long id, @RequestBody Cita cita) {
        if (!citaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cita.setId(id);
        Cita updatedCita = citaService.save(cita);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        if (!citaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        citaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCitas() {
        citaService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
