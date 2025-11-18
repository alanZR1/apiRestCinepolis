package com.mx.proyectoCinepolis.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.proyectoCinepolis.dao.PeliculasDao;
import com.mx.proyectoCinepolis.model.Peliculas;

@Service
public class PeliculasServImp {

    @Autowired
    PeliculasDao peliculaDao;

    @Transactional(readOnly = true)
    public List<Peliculas> mostrar() {
        List<Peliculas> listaPeliculas = peliculaDao.findAll();
        return listaPeliculas;
    }
    

	@Transactional
	public String guardar (Peliculas pelicula) {
		boolean bandera = false;
		
		for (Peliculas p:peliculaDao.findAll()) {
			// si tienes tipos de datos primitivos y estan parseados compararlos con .equals
			if (p.getNombre().equals(pelicula.getNombre())) {
				return "nombreYaExiste";
			}
			if (!bandera) {
				peliculaDao.save(pelicula);
			}
		}
		return "guardado";
	}
	

	@Transactional(readOnly = true)
	public Peliculas buscarXid(Long idPelicula) {	
		Peliculas pelicula = peliculaDao.findById(idPelicula).orElse(null);
		return pelicula;
	}
	

	@Transactional
	public boolean editar (Peliculas pelicula) {
		Peliculas peliculaEncontrada = peliculaDao.findById(pelicula.getIdPelicula()).orElse(null);
		
		if (peliculaEncontrada != null) {	
			peliculaDao.save(pelicula);
			return true;
		}else {
			return false;
		}
	}
	

	@Transactional
	public boolean eliminar (Long idPelicula) {	
		Peliculas peliculaEncontrada = buscarXid(idPelicula);
		
		if (peliculaEncontrada != null) {
			peliculaDao.delete(peliculaEncontrada);
			return true;
		}else {
			return false;
		}
	}

	
	@Transactional(readOnly = true)
	public Peliculas buscarXnombre(String nombre) {
		return peliculaDao.findByNombre(nombre);
	}

	
	@Transactional(readOnly = true)
	public List<Peliculas> buscarXfechaEstreno(Date fechaEstreno) {
	    return peliculaDao.findByFechaEstreno(fechaEstreno);
	}	
	
	
	@Transactional
	public void eliminarXnombre(String nombre) {   
	    peliculaDao.deleteByNombre(nombre);
	}
}