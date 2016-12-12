package com.proyecto.integrador.wayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class encuesta extends AppCompatActivity {
    private String nombreenc;
    private String cedulaenc;
    private String edadenc;
    private String telefonoenc;
    private String generoenc;
    private String cargoenc;
    private String descripcionenc;


    public encuesta(String nombreenc, String cedulaenc, String edadenc, String telefonoenc, String generoenc, String cargoenc, String descripcionenc) {
        this.nombreenc = nombreenc;
        this.cedulaenc = cedulaenc;
        this.edadenc = edadenc;
        this.telefonoenc = telefonoenc;
        this.generoenc = generoenc;
        this.cargoenc = cargoenc;
        this.descripcionenc = descripcionenc;
    }

    public String getEdadenc() {
        return edadenc;
    }

    public void setEdadenc(String edadenc) {
        this.edadenc = edadenc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
    }

    public String getNombreenc() {
        return nombreenc;
    }

    public void setNombreenc(String nombreenc) {
        this.nombreenc = nombreenc;
    }

    public String getTelefonoenc() {
        return telefonoenc;
    }

    public void setTelefonoenc(String telefonoenc) {
        this.telefonoenc = telefonoenc;
    }

    public String getGeneroenc() {
        return generoenc;
    }

    public void setGeneroenc(String generoenc) {
        this.generoenc = generoenc;
    }

    public String getDescripcionenc() {
        return descripcionenc;
    }

    public void setDescripcionenc(String descripcionenc) {
        this.descripcionenc = descripcionenc;
    }

    public String getCedulaenc() {
        return cedulaenc;
    }

    public void setCedulaenc(String cedulaenc) {
        this.cedulaenc = cedulaenc;
    }

    public String getCargoenc() {
        return cargoenc;
    }

    public void setCargoenc(String cargoenc) {
        this.cargoenc = cargoenc;
    }

    public String ImprimirEncuesta(){
        String res = "";
        res =  "Encuesta : {"+",\n";
        res += "nombre : "+this.nombreenc+",\n";
        res += "cedula : "+this.cedulaenc+",\n";
        res += "edad : "+this.edadenc+",\n";
        res += "telefono : "+this.telefonoenc+",\n";
        res += "genero : "+this.generoenc+",\n";
        res += "cargo : "+this.cargoenc+",\n";
        res += "descripcion : "+this.descripcionenc+"}";
        return res;
    }
}
