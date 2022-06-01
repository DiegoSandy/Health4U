package com.example.health4u;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TareaAsync extends AsyncTask {
    private static Object[] obs;
    public static String parametros;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Object doInBackground(Object[] objects) {
        obs=objects;
        getdata();

        return null;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void getdata(){
        String h,h1,m,m1;

        AdminSQLite administrador=Inicio.BD();
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        Calendar c=Calendar.getInstance();

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hh:mm");
        String actualSis= dtf.format(LocalDateTime.now());


        Cursor cursorDb=BaseDeDatos.rawQuery("select nombreMedicamento, dosisMed, fechaInicioMed, fechaFinMed from medicamento where '"+actualSis+"'=horaInicio", null);
        String data="";
        if(cursorDb.moveToFirst()) {
            do {
                //Cursor cursorbd=VerificarBD();
                String name = cursorDb.getString(0) + "";
                String dosis = cursorDb.getString(1) + "";
                String fIni = cursorDb.getString(2) + "";
                String fFin = cursorDb.getString(3) + "";

                data = "Nombre de Medicamento:" +name + "\nDosis:" + dosis + "\nFecha Inicio:" + fIni + "\nFecha Fin" + fFin;


            }while (cursorDb.moveToNext());

        }
        BaseDeDatos.close();


    }

}
