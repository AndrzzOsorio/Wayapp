package com.proyecto.integrador.wayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CorralActivity extends AppCompatActivity {
    EditText animal;
    EditText CantidadAnimales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corral);
        animal = (EditText) findViewById(R.id.txtAnimal);
        CantidadAnimales = (EditText) findViewById(R.id.txtcantidadA);
    }
}
