package com.biblioteca.backend.repository;

import com.biblioteca.backend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    boolean existsByCedula(String cedula);
}
