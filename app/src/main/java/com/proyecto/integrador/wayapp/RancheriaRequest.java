package com.proyecto.integrador.wayapp;

/**
 * Created by Andres on 26/11/2016.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;

/**
 * Created by Andres on 23/06/2016.
 */
public class RancheriaRequest extends StringRequest {
    private static final String COBRO_REQUEST_URL="http://10.11.64.210:8080/WayAppServidor/rest/ConsultarRancherias";
    private Map<String,String> params;

    public RancheriaRequest(Response.Listener<String> listener){
        super(Method.POST, COBRO_REQUEST_URL,listener,null);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
