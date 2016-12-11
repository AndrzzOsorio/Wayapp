package com.proyecto.integrador.wayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EducativoActivity extends AppCompatActivity {
    EditText NombreCentro;
    EditText CantidadEstudiantes;
    EditText MaterialCentro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educativo);
        NombreCentro = (EditText) findViewById(R.id.txtnombre);
        CantidadEstudiantes = (EditText) findViewById(R.id.txtcantidadE);
        MaterialCentro = (EditText) findViewById(R.id.txtMaterialC);
    }
}
