package com.biblioteca.backend.repository;

import com.biblioteca.backend.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, String> {
    boolean existsById(String id);
}
