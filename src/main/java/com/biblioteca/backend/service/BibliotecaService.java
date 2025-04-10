package com.biblioteca.backend.service;

import com.biblioteca.backend.model.Material;
import com.biblioteca.backend.model.Movimiento;
import com.biblioteca.backend.model.Persona;

import java.util.List;

public interface BibliotecaService {

    // Materiales
    Material registrarMaterial(Material material);
    Material incrementarCantidad(String id, int cantidad);
    List<Material> listaMateriales();


    // Personas
    Persona registrarPersona(Persona persona);
    void eliminarPersona(String cedula);
    List<Persona> listaPersonas();


    // Préstamos y devoluciones
    void registrarPrestamo(String cedula, String idMaterial);
    void registrarDevolucion(String cedula, String idMaterial);

    // Consulta de historial
    List<Movimiento> obtenerHistorial();




}
