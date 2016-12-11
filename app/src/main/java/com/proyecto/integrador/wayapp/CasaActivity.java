package com.proyecto.integrador.wayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class CasaActivity extends AppCompatActivity {
    EditText MaterialCasa;
    EditText CantidadHabitaciones;
    EditText CantidadPersonas;
    CheckBox Cocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa);
        MaterialCasa = (EditText) findViewById(R.id.txtMaterial);
        CantidadHabitaciones = (EditText) findViewById(R.id.txtcantidadh);
        CantidadPersonas = (EditText) findViewById(R.id.txtCantidadP);
        Cocina = (CheckBox) findViewById(R.id.cbcocina);

    }
}
