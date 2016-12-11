package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 11/12/2016.
 */

public class Cultivo {
    private double latitud;
    private double longitud;
    private String dueno;
    private String fruto;
    private String area;

    public Cultivo(double latitud, double longitud, String dueno, String fruto,String area){
        this.setLatitud(latitud);
        this.setLongitud(longitud);
        this.setDueno(dueno);
        this.setFruto(fruto);
        this.setArea(area);
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

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public String getFruto() {
        return fruto;
    }

    public void setFruto(String fruto) {
        this.fruto = fruto;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String ToInformation(){
        String informacion = "Dueño: "+dueno+"\n"+
                "Fruto: "+fruto+"\n"+
                "area: "+area+" metros cuadrados"+"\n";
        return informacion;
    }
}
