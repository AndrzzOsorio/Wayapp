package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 11/12/2016.
 */

public class Corral {
    private double latitud;
    private double longitud;
    private String animal;
    private String cantidadaniales;

    public Corral (double latitud, double longitud,String animal,String cantidadaniales){
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setAnimal(animal);
        this.setCantidadaniales(cantidadaniales);

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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCantidadaniales() {
        return cantidadaniales;
    }

    public void setCantidadaniales(String cantidadaniales) {
        this.cantidadaniales = cantidadaniales;
    }

    public String ToInformation(){
        String informacion = "Animal: "+animal+"\n"+
                "Cantidad: "+cantidadaniales;
        return informacion;
    }
}
