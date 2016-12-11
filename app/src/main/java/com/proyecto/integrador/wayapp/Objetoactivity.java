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
                    case 1:
                        Intent intent1 = new Intent(Objetoactivity.this,EducativoActivity.class);
                        Objetoactivity.this.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Objetoactivity.this,CorralActivity.class);
                        Objetoactivity.this.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Objetoactivity.this,CultivoActivity.class);
                        Objetoactivity.this.startActivity(intent3);
                        break;



                }
            }
        });





    }
}
