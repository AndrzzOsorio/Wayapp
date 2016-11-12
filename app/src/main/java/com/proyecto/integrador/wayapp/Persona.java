package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 1/11/2016.
 */

public class Persona {
    private String id;
    private String nombre;
    private String apellido;
    private String edad;
    private String genero;
    private String titulo;
    private String clan;

    public Persona (String id, String nombre, String apellido, String edad, String genero, String titulo, String clan){
        this.setId(id);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEdad(edad);
        this.setGenero(genero);
        this.setTitulo(titulo);
        this.setClan(clan);
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
