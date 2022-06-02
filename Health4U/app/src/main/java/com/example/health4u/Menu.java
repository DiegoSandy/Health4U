package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

   //public static AdminSQLite administrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_central);
        //administrador=new AdminSQLite(this, "registro", null, 1);
    }
    /*public static AdminSQLite BD(){
        return administrador;
    }*/

    public void MEDICAMENTO(View view){
        Intent medi = new Intent(this, Medicamento1.class);
        startActivity(medi);
    }
    public void CITA(View v) {
        Intent pcita = new Intent(this, ProgramarCita.class);
        startActivity(pcita);
    }

}