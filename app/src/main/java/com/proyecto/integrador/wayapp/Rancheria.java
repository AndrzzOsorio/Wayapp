package com.proyecto.integrador.wayapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andres on 24/10/2016.
 */

public class Rancheria implements Serializable {
    private int id;
    private String nombre;
    private ArrayList<LatLng> delimitacion;
    private ArrayList<Elemento> elementos;

    public Rancheria(int id, String nombre){
        this.setId(id);
        this.nombre = nombre;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<LatLng> getDelimitacion() {
        return delimitacion;
    }

    public void setDelimitacion(ArrayList<LatLng> delimitacion) {
        this.delimitacion = delimitacion;
    }

    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elemento> elementos) {
        this.elementos = elementos;
    }
}
