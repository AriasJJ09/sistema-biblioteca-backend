package com.biblioteca.backend.repository;

import com.biblioteca.backend.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findAllByOrderByFechaMovimientoDesc();
}