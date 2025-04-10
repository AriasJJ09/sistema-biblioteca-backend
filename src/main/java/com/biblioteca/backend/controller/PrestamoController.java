package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.Movimiento;
import com.biblioteca.backend.service.BibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final BibliotecaService service;

    // Registrar préstamo
    @PostMapping("/registrar")
    public ResponseEntity<Void> registrarPrestamo(
            @RequestParam String cedula,
            @RequestParam String idMaterial
    ) {
        service.registrarPrestamo(cedula, idMaterial);
        return ResponseEntity.ok().build();
    }

    // Registrar devolución
    @PostMapping("/devolver")
    public ResponseEntity<Void> registrarDevolucion(
            @RequestParam String cedula,
            @RequestParam String idMaterial
    ) {
        service.registrarDevolucion(cedula, idMaterial);
        return ResponseEntity.ok().build();
    }

    // Obtener historial de movimientos
    @GetMapping("/historial")
    public ResponseEntity<List<Movimiento>> obtenerHistorial() {
        return ResponseEntity.ok(service.obtenerHistorial());
    }
}
