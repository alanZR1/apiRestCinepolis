package com.mx.proyectoCinepolis.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.proyectoCinepolis.model.Peliculas;

// Repositorio para manejar operaciones CRUD sobre la entidad Pelicula

public interface PeliculasDao extends JpaRepository<Peliculas, Long> {

	public Peliculas findByNombre(String nombre);
	public List<Peliculas> findByFechaEstreno(Date fechaEstreno);
	void deleteByNombre(String nombre);
}