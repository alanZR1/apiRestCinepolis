package com.mx.proyectoCinepolis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "PELICULAS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementable
    @Column(name = "ID_PELICULA", columnDefinition = "INT", nullable = false)
    private Long idPelicula;

    @Column(name = "NOMBRE",columnDefinition = "VARCHAR2(100)", nullable = false)
    private String nombre;

    @Column(name = "FECHA_ESTRENO", columnDefinition = "DATE", nullable = false)
    private Date fechaEstreno;

    @Column(name = "PRECIO", columnDefinition = "NUMBER(6,2)", nullable = false)
    private Float precio;
}