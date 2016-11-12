package com.proyecto.integrador.wayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Objetoactivity extends AppCompatActivity {
    ListView listaobjetos;
    ArrayAdapter<String> adaptador;
    String[] objetos = {
            "Casa",
            "Centro educativo",
            "Corral",
            "Cultivo",
            "Panel solar",
            "Fuente hidrica",
            "Molino de viento",
            "Mina",
            "Generador",
            "Crear elemento"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetoactivity);
        adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                objetos);
        listaobjetos = (ListView) findViewById(R.id.listaobjetos);
        listaobjetos.setAdapter(adaptador);
        listaobjetos.setOnItemClickListener(  new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(Objetoactivity.this,CasaActivity.class);
                        Objetoactivity.this.startActivity(intent);
                        break;


                }
            }
        });





    }
}
