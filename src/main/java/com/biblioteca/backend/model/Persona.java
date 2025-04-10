package com.biblioteca.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona {

    @Id
    @Column(nullable = false, unique = true)
    private String cedula;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column(name = "prestamos_activos", nullable = false)
    private int prestamosActivos;

    public enum Rol {
        ESTUDIANTE,
        PROFESOR,
        ADMINISTRATIVO
    }
}
