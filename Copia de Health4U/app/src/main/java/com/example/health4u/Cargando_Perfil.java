package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Cargando_Perfil extends AppCompatActivity {

    TextView text;
    Timer timeCargando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando_perfil);
        AdminSQLite administrador=Inicio.BD();
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        Cursor bd=BaseDeDatos.rawQuery("select nombrePerfil from datosPerfil", null);
        text=(TextView)findViewById(R.id.editTextTextPersonName);
        bd.moveToFirst();
        text.setText(bd.getString(0));

        timeCargando = new Timer();
        timeCargando.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent next=new Intent(Cargando_Perfil.this, Menu.class);
                startActivity(next);
                finish();
            }
        }, 2500);
    }
}