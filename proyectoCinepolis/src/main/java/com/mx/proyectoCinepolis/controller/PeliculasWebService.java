package com.mx.proyectoCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.proyectoCinepolis.model.Peliculas;
import com.mx.proyectoCinepolis.service.PeliculasServImp;

@RestController
@RequestMapping(path = "PeliculasWebService")
@CrossOrigin
public class PeliculasWebService {

    //@RestController: controlador de solicitudes REST
    //@RequestMapping: ruta base del servicio
    //@CrossOrigin: permite peticiones desde otros orígenes (evita bloqueos del navegador)

    @Autowired
    PeliculasServImp peliculasServImp;

    // Peticiones: GET, POST, PUT...
    // URL de prueba en Postman:
    // http://localhost:9000/PeliculasWebService/mostrar

    @GetMapping(path = "mostrar")
    public List<Peliculas> mostrar() {
        return peliculasServImp.mostrar();
    }
}

