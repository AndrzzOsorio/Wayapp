package com.proyecto.integrador.wayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CultivoActivity extends AppCompatActivity {
    EditText Dueño;
    EditText Fruto;
    EditText Area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivo);
        Dueño = (EditText) findViewById(R.id.txtdueño);
        Fruto = (EditText) findViewById(R.id.txtfrutos);
        Area = (EditText) findViewById(R.id.txtAreaC);
    }
}
