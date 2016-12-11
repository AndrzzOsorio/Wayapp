package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 11/12/2016.
 */

public class Escuela {
    private double latitud;
    private double longitud;
    private String material;
    private String cantidadestudiantes;
    private String nombre;

    public Escuela (double latitud, double longitud, String material,String cantidadestudiantes,String nombre){
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setMaterial(material);
        this.setCantidadestudiantes(cantidadestudiantes);
        this.setNombre(nombre);
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

    public String getCantidadestudiantes() {
        return cantidadestudiantes;
    }

    public void setCantidadestudiantes(String cantidadestudiantes) {
        this.cantidadestudiantes = cantidadestudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String ToInformation(){
        String informacion = "Nombre: "+nombre+"\n"+
                "Estudiantes: "+cantidadestudiantes+"\n"+
                "Material: "+material+"\n";
        return informacion;

    }
}
