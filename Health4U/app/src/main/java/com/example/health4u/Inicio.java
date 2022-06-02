package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {

    public static AdminSQLite administrador;
    Intent intentService;

   // public static BdConsultas bdConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inicio);
        administrador=new AdminSQLite(this, "registro", null, 1);

        // bdConsultas= new BdConsultas(this, "registro", null, 1);
        //Agregar animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);
        TextView textView14 = findViewById(R.id.textView0);
        ImageView imageView3 = findViewById(R.id.LogoIcono);

        textView14.setAnimation(animacion2);
        imageView3.setAnimation(animacion1);

        //intentService=new Intent(Inicio.this, notiService.class);
        //startService(intentService);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
                Cursor cursorDb=BaseDeDatos.rawQuery("select nombrePerfil from datosPerfil ", null);
                if(cursorDb!=null && cursorDb.getCount()>0){
                    Intent intent = new Intent(Inicio.this,Cargando_Perfil.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent = new Intent(Inicio.this,Inicio2.class);
                    startActivity(intent);

                    finish();
                }

            }
        },4000);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


    }
    public static AdminSQLite BD(){
        return administrador;
    }
}