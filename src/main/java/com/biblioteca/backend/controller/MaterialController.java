package com.biblioteca.backend.controller;

import com.biblioteca.backend.model.Material;
import com.biblioteca.backend.service.BibliotecaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
@RequiredArgsConstructor
public class MaterialController {

    private final BibliotecaService service;


    @GetMapping()
    public ResponseEntity<List<Material>> listarMateriales() {
        return ResponseEntity.ok(service.listaMateriales());
    }

    // Registrar nuevo material
    @PostMapping("/registrar")
    public ResponseEntity<Material> registrarMaterial(@RequestBody Material material) {
        return ResponseEntity.ok(service.registrarMaterial(material));
    }

    // Incrementar cantidad disponible de un material
    @PutMapping("/incrementar/{id}")
    public ResponseEntity<Material> incrementarCantidad(
            @PathVariable String id,
            @RequestParam int cantidad
    ) {
        return ResponseEntity.ok(service.incrementarCantidad(id, cantidad));
    }

}
