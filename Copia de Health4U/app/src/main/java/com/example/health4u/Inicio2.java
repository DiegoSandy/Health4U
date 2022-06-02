package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio2 extends AppCompatActivity {

    //static boolean iniciar=false;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio2);
    }

    /*public static void setIniciar(){
        iniciar=true;
    }*/
    //Método del botón Mas
    public void Mas(View view){
        Intent mas = new Intent (this,Ingresar_Nombre.class);
        startActivity(mas);
        finish();
    }
}