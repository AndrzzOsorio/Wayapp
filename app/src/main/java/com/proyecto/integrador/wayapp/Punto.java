package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 17/10/2016.
 */

public class Punto {
    double latitud;
    double longitud;
    String nombre;
    String descripcion;


    public Punto(double latitud, double longitud, String descripcion){
        this.latitud = latitud;
        this.longitud=longitud;
        this.descripcion=descripcion;
    }

    public double getLongitud(){
        return this.longitud;
    }

    public double getLatitud(){
        return this.latitud;
    }

    

}
