package com.mx.proyectoCinepolis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.proyectoCinepolis.model.Peliculas;

// Repositorio para manejar operaciones CRUD sobre la entidad Pelicula

public interface PeliculasDao extends JpaRepository<Peliculas, Long> {

}