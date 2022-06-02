package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IngresarFechaNac extends AppCompatActivity {
    public static EditText Date;
    public Verificador screenDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_fecha_nac);
        Date = (EditText) findViewById(R.id.IntroFecha);
    }
    //Método Boton Siguiente
    public void Siguiente(View view) {
       screenDate = new Verificador();
        String ver = Date.getText().toString();
        if(!ver.isEmpty()){
            if (screenDate.verFormato(ver)) {
                if (screenDate.verificadorFechaValida(ver)) {
                    if(screenDate.fecPermitida(ver)){
                        Intent siguiente = new Intent(this, IngresarEnfermedad.class);
                        startActivity(siguiente);
                        finish();
                    }else{
                        Date.setError(screenDate.ErrorFechPermitida(ver));
                    }
                }else{
                    Date.setError(screenDate.DefinidorErrorFecha(ver));
                }
            }else{
                Date.setError(screenDate.definidorErrorFormatoFecha(ver));
            }
        }else
            Date.setError("ingrese fecha");



    }
    //Para la base de datos
    public static String getText(){
        String fech = Date.getText().toString();
        Date.setText("");
        return fech;
    }
    //Método para el botón Cancelar
    public void Bienvenido(View view){
        Intent bienvenido = new Intent(this,Inicio2.class);
        startActivity(bienvenido);
        finish();
    }
}