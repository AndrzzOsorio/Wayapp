package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 26/11/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres on 23/06/2016.
 */
public class RancheriaRequest extends StringRequest {
    private static final String RANCHERIA_REQUEST_URL1="http://192.168.1.76:8080/WayAppServidor/rest/ConsultarRancherias";
    private static final String RANCHERIA_REQUEST_URL2="http://192.168.1.76:8080/WayAppServidor/rest/RegistrarRancheria";
     Map<String,String> params;

    public RancheriaRequest(Response.Listener<String> listener){
        super(Method.POST, RANCHERIA_REQUEST_URL1,listener,null);
    }

    public RancheriaRequest(Response.Listener<String> listener, String id, String nombre){
        super(Method.POST, RANCHERIA_REQUEST_URL2,listener,null);
        params = new HashMap<>();
        params.put("id_rancheria",id);
        params.put("nombre",nombre);
        params.put("detalles","esto es una rancheria");

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
