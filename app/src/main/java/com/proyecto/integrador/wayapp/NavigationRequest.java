package com.proyecto.integrador.wayapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres on 1/12/2016.
 */

public class NavigationRequest extends StringRequest {
    private static final String NAVIGATION_REQUEST_URL1="http://192.168.1.76:8080/WayAppServidor/rest/RegistrarPuntos";
    Map<String,String> params;

    public NavigationRequest(Response.Listener<String> listener, ArrayList<Punto> puntos,String id) {
        super(Method.POST, NAVIGATION_REQUEST_URL1, listener, null);
        params = new HashMap<>();
        params.put("arreglo",arrayToJson(puntos,id));
        params.put("tipo","delimitacion");

    }

    public String arrayToJson(ArrayList<Punto> puntos, String id){
      String json = "[";
        for (int i=0; i< puntos.size();i++){
            if(i == puntos.size()-1){
                json+=puntos.get(i).toJson(id);
                }
            else{
                json+=puntos.get(i).toJson(id)+",";
            }
        }
        json+="]";
        return json;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


