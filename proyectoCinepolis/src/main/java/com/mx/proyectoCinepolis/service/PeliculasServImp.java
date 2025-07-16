package com.mx.proyectoCinepolis.service;

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
}