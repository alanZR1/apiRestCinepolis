package com.mx.proyectoCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.proyectoCinepolis.model.Peliculas;
import com.mx.proyectoCinepolis.service.PeliculasServImp;

@RestController
@RequestMapping(path = "PeliculasWebService")
@CrossOrigin

public class PeliculasWebService {

    //@RestController: controlador de solicitudes REST recibe request http y devuelve json
    //@RequestMapping: ruta base del servicio
    //@CrossOrigin: permite peticiones desde otros orígenes (evita bloqueos del navegador)

    @Autowired // inyección de dependencias para usar los métodos del servicio
    PeliculasServImp peliculasServImp;

    // Peticiones: GET, POST, PUT...
    // URL de prueba en Postman:
    // http://localhost:9000/PeliculasWebService/mostrar

	// los metodos la mejor forma es verlos como acciones http 
    @GetMapping(path = "mostrar")
    public List<Peliculas> mostrar() {
        return peliculasServImp.mostrar();
    }
    
    // http://localhost:9000/PeliculasWebService/guardar
	@PostMapping (path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula){  // @RequestBody: convierte el JSON del cuerpo de la solicitud en un objeto Peliculas	
		String response = peliculasServImp.guardar(pelicula);
		
		if (response.equals("nombreYaExiste")) {
			return new ResponseEntity<>("Ese nombre ya existe, no se puede guardar ", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(pelicula, HttpStatus.CREATED); // devuelve el objeto guardado y un código de estado HTTP 201 (Created)
		}
	}

	
	@GetMapping(path = "buscar")
	public Peliculas buscarXid(@RequestBody Peliculas pelicula) {
		return peliculasServImp.buscarXid(pelicula.getIdPelicula());
	}
	
	
	@PutMapping( path = "editar")
	public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
		boolean response = peliculasServImp.editar(pelicula); // devuelve true si se editó correctamente, false si no se encontró el registro para editar
		
		if (response == false) {
			return new ResponseEntity<>("Ese registro no existe, no se puede editar", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
		}
	}
	

	@DeleteMapping(path = "eliminar")
	public ResponseEntity<String> eliminar (@RequestBody Peliculas pelicula){
		boolean response = peliculasServImp.eliminar(pelicula.getIdPelicula());
		
		if (response == true) {
			return new ResponseEntity<>("Registro eliminado con exito", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Registro no eliminado, no existe", HttpStatus.NOT_FOUND);
		}
	}
	
	// http://localhost:9000/PeliculasWebService/buscarXnombre/nombre
	@GetMapping(path = "buscarXnombre/{nombre}")
	public Peliculas buscarXnombre (@PathVariable("nombre") String nombre) {
		return peliculasServImp.buscarXnombre(nombre);
	}
	

	// http://localhost:9000/PeliculasWebService/buscarXnombre/fecha
	@PostMapping(path = "buscarXfechaEstreno")
    public List<Peliculas> buscarXfechaEstreno(@RequestBody Peliculas pelicula) {
        return peliculasServImp.buscarXfechaEstreno(pelicula.getFechaEstreno());
    }
	

	@DeleteMapping("eliminarXnombre/{nombre}")
	public ResponseEntity<String> eliminarXnombre(@PathVariable("nombre") String nombre) {
	    try {
	        peliculasServImp.eliminarXnombre(nombre);
	        return ResponseEntity.ok("Película eliminada: " + nombre);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Error al eliminar la película: " + e.getMessage());
	    }
	}
}

















