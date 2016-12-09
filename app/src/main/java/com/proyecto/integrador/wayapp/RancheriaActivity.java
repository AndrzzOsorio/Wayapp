package com.proyecto.integrador.wayapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RancheriaActivity extends AppCompatActivity {

    int id;
    FloatingActionButton load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rancheria);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ListView lista =  (ListView)findViewById(R.id.listarancherias);
        load = (FloatingActionButton) findViewById(R.id.load);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nueva);
        fab.setOnClickListener(new View.OnClickListener() {
            public ArrayList<Rancheria> rancherias = new ArrayList<>();
            @Override
            public void onClick(View view) {
                if(rancherias.isEmpty()){
                    id = 1;
                    solicitarnombre();


                }
                else{
                    id = rancherias.indexOf(rancherias.size()-1);
                    solicitarnombre();
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<Rancheria> rancherias = new ArrayList<>();
                            JSONArray JsonResponse = new JSONArray(response);
                            for (int i =0;i<JsonResponse.length();i++){
                                JSONObject row = JsonResponse.getJSONObject(i);

                                rancherias.add(new Rancheria(row.getString("id_rancheria"),row.getString("nombre")));

                            }
                            if (!rancherias.isEmpty()){
                                 setadapter(rancherias);
                                }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RancheriaRequest request = new RancheriaRequest(listener);
                RequestQueue queue = Volley.newRequestQueue(RancheriaActivity.this);
                queue.add(request);

            }
        });

        load.performClick();


    }

    private String solicitarnombre(){
        final String[] m_Text = {""};
        final AlertDialog.Builder[] builder = {new AlertDialog.Builder(this)};
        builder[0].setTitle("Nombre Rancheria");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder[0].setView(input);

// Set up the buttons
        builder[0].setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Rancheria r = new Rancheria(id+"",input.getText().toString());

                Intent intent = new Intent(RancheriaActivity.this,NavigationActivity.class);
                intent.putExtra("rancheria",r);
                RancheriaActivity.this.startActivity(intent);

            }
        });
        builder[0].setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder[0].show();
        return m_Text[0];
    }

    public ArrayList<String> arrayToString(ArrayList<Rancheria> rancherias){
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0 ; i<rancherias.size(); i++){
            s.add(rancherias.get(i).getNombre());
        }
        return s;
    }

    public void setadapter (final ArrayList<Rancheria> rancherias){
        ListView lista =  (ListView)findViewById(R.id.listarancherias);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                RancheriaActivity.this,
                android.R.layout.simple_list_item_1,
                arrayToString(rancherias));
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(  new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RancheriaActivity.this, NavigationActivity.class);
                intent.putExtra("rancheria",rancherias.get(position));
                RancheriaActivity.this.startActivity(intent);
            }
        });
    }




}
