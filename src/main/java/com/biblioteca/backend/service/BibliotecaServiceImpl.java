package com.biblioteca.backend.service;

import com.biblioteca.backend.model.Material;
import com.biblioteca.backend.model.Movimiento;
import com.biblioteca.backend.model.Persona;
import com.biblioteca.backend.model.Movimiento.TipoMovimiento;
import com.biblioteca.backend.model.Persona.Rol;
import com.biblioteca.backend.repository.MaterialRepository;
import com.biblioteca.backend.repository.MovimientoRepository;
import com.biblioteca.backend.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BibliotecaServiceImpl implements BibliotecaService {

    private final MaterialRepository materialRepo;
    private final PersonaRepository personaRepo;
    private final MovimientoRepository movimientoRepo;

    @Override
    public Material registrarMaterial(Material material) {
        if (materialRepo.existsById(material.getId())) {
            throw new RuntimeException("Ya existe un material con ese ID.");
        }
        material.setFechaRegistro(LocalDate.now());
        material.setCantidadDisponible(material.getCantidadRegistrada());
        return materialRepo.save(material);
    }

    @Override
    public Material incrementarCantidad(String id, int cantidad) {
        Material mat = materialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado."));
        mat.setCantidadRegistrada(mat.getCantidadRegistrada() + cantidad);
        mat.setCantidadDisponible(mat.getCantidadDisponible() + cantidad);
        return materialRepo.save(mat);
    }

    @Override
    public Persona registrarPersona(Persona persona) {
        if (personaRepo.existsById(persona.getCedula())) {
            throw new RuntimeException("Ya existe una persona con esa cédula.");
        }
        persona.setPrestamosActivos(0);
        return personaRepo.save(persona);
    }

    @Override
    public void eliminarPersona(String cedula) {
        Persona persona = personaRepo.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada."));
        if (persona.getPrestamosActivos() > 0) {
            throw new RuntimeException("No se puede eliminar: la persona tiene préstamos activos.");
        }
        personaRepo.delete(persona);
    }

    @Override
    @Transactional
    public void registrarPrestamo(String cedula, String idMaterial) {
        Persona persona = personaRepo.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada."));
        Material material = materialRepo.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material no encontrado."));

        int maxPrestamos = switch (persona.getRol()) {
            case ESTUDIANTE -> 5;
            case PROFESOR -> 3;
            case ADMINISTRATIVO -> 1;
        };

        if (persona.getPrestamosActivos() >= maxPrestamos) {
            throw new RuntimeException("Ha alcanzado el límite de préstamos.");
        }

        if (material.getCantidadDisponible() <= 0) {
            throw new RuntimeException("Material no disponible.");
        }

        persona.setPrestamosActivos(persona.getPrestamosActivos() + 1);
        material.setCantidadDisponible(material.getCantidadDisponible() - 1);

        personaRepo.save(persona);
        materialRepo.save(material);

        Movimiento mov = Movimiento.builder()
                .idMaterial(idMaterial)
                .tituloMaterial(material.getTitulo())
                .cedulaPersona(cedula)
                .nombrePersona(persona.getNombre())
                .fechaMovimiento(LocalDateTime.now())
                .tipo(TipoMovimiento.PRESTAMO)
                .build();
        movimientoRepo.save(mov);
    }

    @Override
    @Transactional
    public void registrarDevolucion(String cedula, String idMaterial) {
        Persona persona = personaRepo.findById(cedula)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada."));
        Material material = materialRepo.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material no encontrado."));

        if (persona.getPrestamosActivos() <= 0) {
            throw new RuntimeException("La persona no tiene préstamos activos.");
        }

        persona.setPrestamosActivos(persona.getPrestamosActivos() - 1);
        material.setCantidadDisponible(material.getCantidadDisponible() + 1);

        personaRepo.save(persona);
        materialRepo.save(material);

        Movimiento mov = Movimiento.builder()
                .idMaterial(idMaterial)
                .tituloMaterial(material.getTitulo())
                .cedulaPersona(cedula)
                .nombrePersona(persona.getNombre())
                .fechaMovimiento(LocalDateTime.now())
                .tipo(TipoMovimiento.DEVOLUCION)
                .build();
        movimientoRepo.save(mov);
    }

    @Override
    public List<Movimiento> obtenerHistorial() {
        return movimientoRepo.findAllByOrderByFechaMovimientoDesc();
    }

    @Override
    public List<Persona> listaPersonas() {
        return personaRepo.findAll();
    }
    @Override
    public List<Material> listaMateriales() {
        return materialRepo.findAll();
    }

}
