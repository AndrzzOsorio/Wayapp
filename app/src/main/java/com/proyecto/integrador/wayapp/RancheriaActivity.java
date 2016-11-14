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
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RancheriaActivity extends AppCompatActivity {
private ArrayList<Rancheria> rancherias = new ArrayList<>();
    int id;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rancheria);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        lista =  (ListView)findViewById(R.id.listarancherias);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.nueva);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rancherias.isEmpty()){
                    id = 1;
                    solicitarnombre();


                }
                else{
                    id = rancherias.indexOf(rancherias.size()-1);
                    Intent intent = new Intent(RancheriaActivity.this,NavigationActivity.class);
                    intent.putExtra("id",id);
                    RancheriaActivity.this.startActivity(intent);
                }


            }
        });
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
                Rancheria r = new Rancheria(id,input.getText().toString());

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

}
