package com.proyecto.integrador.wayapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allejo on 9/12/2016.
 */

public class CasaRequest extends StringRequest{
    private static final String RANCHERIA_REQUEST_URL1="http://192.168.1.76:8080/WayAppServidor/rest/ConsultarCasas";
    private static final String RANCHERIA_REQUEST_URL2="http://192.168.1.76:8080/WayAppServidor/rest/RegistrarCasas";
    Map<String,String> params;

    public CasaRequest(Response.Listener<String> listener){
        super(Method.POST, RANCHERIA_REQUEST_URL1,listener,null);
    }

    public CasaRequest(Response.Listener<String> listener, String Material, String CantidadPersonas, String CantidadHabitaciones, String
                       Cocina){
        super(Method.POST, RANCHERIA_REQUEST_URL2,listener,null);
        params = new HashMap<>();
        params.put("Material_Casa",Material);
        params.put("Cantidad_Personas",CantidadPersonas);
        params.put("Cantidad_Habitaciones",CantidadHabitaciones);
        params.put("Tiene_Cocina",Cocina);

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
