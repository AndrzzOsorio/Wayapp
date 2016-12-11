package com.proyecto.integrador.wayapp;

import java.io.Serializable;

/**
 * Created by Andres on 11/12/2016.
 */

public class Casa implements Serializable{
    private double latitud;
    private double longitud;
    private String material;
    private String cocina;
    private String cantidadpersonas;
    private String cantidadhabitaciones;
    private String observacion;

    public Casa(double latitud, double longitud, String material, String cocina, String cantidadhabitaciones, String cantidadpersonas,String observacion){
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setMaterial(material);
        this.setCocina(cocina);
        this.setCantidadhabitaciones(cantidadhabitaciones);
        this.setCantidadpersonas(cantidadpersonas);
        this.setObservacion(observacion);
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCocina() {
        return cocina;
    }

    public void setCocina(String cocina) {
        this.cocina = cocina;
    }

    public String getCantidadpersonas() {
        return cantidadpersonas;
    }

    public void setCantidadpersonas(String cantidadpersonas) {
        this.cantidadpersonas = cantidadpersonas;
    }

    public String getCantidadhabitaciones() {
        return cantidadhabitaciones;
    }

    public void setCantidadhabitaciones(String cantidadhabitaciones) {
        this.cantidadhabitaciones = cantidadhabitaciones;
    }

    public String ToInformation(){
        String informacion = "Material: "+material+"\n"+
                             "Cocina: "+cocina+"\n"+
                             "Habitaciones:"+cantidadhabitaciones+"\n"+
                             "Habitantes: "+cantidadpersonas+"\n"+
                             "Observacion: "+observacion;
        return informacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
