package com.biblioteca.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_material", nullable = false)
    private String idMaterial;

    @Column(name = "titulo_material", nullable = false)
    private String tituloMaterial;

    @Column(name = "cedula_persona", nullable = false)
    private String cedulaPersona;

    @Column(name = "nombre_persona", nullable = false)
    private String nombrePersona;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    public enum TipoMovimiento {
        PRESTAMO,
        DEVOLUCION
    }
}
