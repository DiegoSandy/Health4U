package com.example.ishealth4u;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IngresarName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_name);
    }
    //Método Boton Siguiente
        public void Siguiente(View view){
        Intent siguiente = new Intent(this, IngresarFechaNac.class);
        startActivity(siguiente);
    }
}