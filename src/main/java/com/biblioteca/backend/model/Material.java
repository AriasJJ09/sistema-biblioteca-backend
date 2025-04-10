package com.biblioteca.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "materiales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "cantidad_registrada", nullable = false)
    private int cantidadRegistrada;

    @Column(name = "cantidad_disponible", nullable = false)
    private int cantidadDisponible;

    @Column(nullable = false)
    private String tipo; // Libro, revista, audiovisual
}
