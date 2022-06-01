package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Ingresar_Nombre extends AppCompatActivity {
    public static EditText name;
    public Verificador screenName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_nombre);
        name = (EditText) findViewById(R.id.introName);
    }
    //Método Boton Siguiente
    public void Siguiente(View view){
        screenName = new Verificador();
        String ver= name.getText().toString();
        if(screenName.maxCaracteres(ver,20)){
            Intent siguiente = new Intent(this, IngresarFechaNac.class);
            startActivity(siguiente);
            finish();
        }else{
            name.setError(screenName.ErrorDatosNoOpcionales(ver,20));
        }

    }
    //Para la base de datos
    public static String getText(){
        String Nom = name.getText().toString();
        name.setText("");
        return Nom;
    }
    //Método para el botón Cancelar
    public void Bienvenido(View view){
        Intent bienvenido = new Intent(this,Inicio2.class);
        startActivity(bienvenido);
        finish();
    }
}