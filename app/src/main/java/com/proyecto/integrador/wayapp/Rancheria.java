package com.proyecto.integrador.wayapp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andres on 24/10/2016.
 */

public class Rancheria implements Serializable {
    private String id;
    private String nombre;
    private ArrayList<Punto> delimitacion;
    private ArrayList<Punto> acceso;
    private ArrayList<Casa> casas;
    private ArrayList<Escuela> escuelas;
    private ArrayList<Corral> corrales;
    private ArrayList<Cultivo> cultivos;
    private ArrayList<Elemento> elementos;

    public Rancheria(String id, String nombre){
        this.setId(id);
        this.nombre = nombre;
        delimitacion = new ArrayList<>();
        acceso = new ArrayList<>();
        casas = new ArrayList<>();
        escuelas = new ArrayList<>();
        corrales = new ArrayList<>();
        cultivos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Punto> getDelimitacion() {
        return delimitacion;
    }

    public void setDelimitacion(ArrayList<Punto> delimitacion) {
        this.delimitacion = delimitacion;
    }

    public ArrayList<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Elemento> elementos) {
        this.elementos = elementos;
    }

    public ArrayList<Punto> getAcceso() {
        return acceso;
    }

    public void setAcceso(ArrayList<Punto> acceso) {
        this.acceso = acceso;
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public void setCasas(ArrayList<Casa> casas) {
        this.casas = casas;
    }

    public ArrayList<Escuela> getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(ArrayList<Escuela> escuelas) {
        this.escuelas = escuelas;
    }

    public ArrayList<Corral> getCorrales() {
        return corrales;
    }

    public void setCorrales(ArrayList<Corral> corrales) {
        this.corrales = corrales;
    }

    public ArrayList<Cultivo> getCultivos() {
        return cultivos;
    }

    public void setCultivos(ArrayList<Cultivo> cultivos) {
        this.cultivos = cultivos;
    }
}
