package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.Material;
import com.biblioteca.backend.model.Persona;
import com.biblioteca.backend.service.BibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final BibliotecaService service;


    @GetMapping()
    public ResponseEntity<List<Persona>> listaPersonas() {
        return ResponseEntity.ok(service.listaPersonas());
    }


    // Registrar nueva persona
    @PostMapping("/registrar")
    public ResponseEntity<Persona> registrarPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(service.registrarPersona(persona));
    }

    // Eliminar persona (solo si no tiene préstamos activos)
    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String cedula) {
        service.eliminarPersona(cedula);
        return ResponseEntity.noContent().build();
    }
}
