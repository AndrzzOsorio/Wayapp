package com.proyecto.integrador.wayapp;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Andres on 1/11/2016.
 */

public class Elemento {
    private int id;
    private String nombre;
    private String descripcion;
    private Map<String, String> atributos;
    private String imagen;
    private double latitud;
    private double longitud;
    private ArrayList<Persona> personas;

    public Elemento(int id, String nombre,String descripcion,String imagen, double latitud,double longitud){
        this.setId(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setAtributos(getAtributos());
        this.setImagen(imagen);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Map<String, String> getAtributos() {
        return atributos;
    }

    public void setAtributos(Map<String, String> atributos) {
        this.atributos = atributos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
}
