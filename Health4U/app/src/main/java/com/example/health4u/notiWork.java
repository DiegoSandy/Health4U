package com.example.health4u;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
public class notiWork extends Worker {
    String data;
    Timer time;
    Long duration;
    TareaAsync ta;
    OneTimeWorkRequest otwr;
    static String hora;
    public static String parametros = "hehe no funciona";
    public static String parametrosCita;
    public static String mensajeNotificaion = "Deslice notificación hacia abajo para ver más detalles";
    public static String mensajeCita="Deslice notificación hacia abajo para ver más detalles";
    public notiWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static void guardarNoti(Long duracion, Data data){

        OneTimeWorkRequest otwr=new OneTimeWorkRequest.Builder(notiWork.class)
                .setInitialDelay(duracion, TimeUnit.MILLISECONDS)
                .setInputData(data).build();
        WorkManager.getInstance().enqueue(otwr);



        Log.d("dur", String.valueOf(duracion));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Result doWork() {

    if(Integer.parseInt(getInputData().getString("tipo"))==1) {
        //detalleNotificacion.setdata(hora);
        oreo("Medicamento", mensajeNotificaion);

        Data data=new Data.Builder()
                //.putString("hora",h)
                .putString("name",Medicamento3.Name)
                .putString("tipo","1").build();
        guardarNoti(Medicamento3.TIEMPO_PERIODO, data);

    }else{
        oreo2(getInputData().getString("name"),mensajeCita);
    }
    Log.d("workMN","done");

        return Result.success();
    }



    private void oreo(String t, String d){
        String id="message";
        NotificationManager nm=(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),id);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setDescription("Notificacion FCM");
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }

        Intent intent = new Intent(getApplicationContext(),detalleNotificacion.class);
        //intent.putExtra("h",hora);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentTitle(t)
                .setTicker("Nueva notificacion")
                .setContentText(d)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ico_4u)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("\n"+Medicamento3.data))
                //.setDefaults(Notification.DEFAULT_SOUND)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);

        Random random = new Random();
        int idNotify=random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());


        //doWork();

    }

    private void oreo2(String t, String d){
        String id="message2";
        NotificationManager nm=(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),id);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(id, "nuevo2", NotificationManager.IMPORTANCE_HIGH);
            nc.setDescription("Notificacion FCM2");
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }

        Intent intent = new Intent(getApplicationContext(),detallNotifCita.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
        //parametrosCita = getcita();
        builder.setContentTitle(t)
                .setTicker("Nueva notificacion2")
                .setContentText(d)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ico_4u)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("\n" + ProgramarCita3.data))
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(pendingIntent);

        Random random = new Random();
        int idNotify=random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getdata(){
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
                if(data!="") {
                    parametros = data;
                }

            }while (cursorDb.moveToNext());

        }
        BaseDeDatos.close();
        return data;
    }
    public String getcita(){
        String hc,hc1,mc,mc1;

        AdminSQLite administrador=Inicio.BD();
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        Calendar c=Calendar.getInstance();
        if(c.get(Calendar.HOUR)<10) {
            hc =String.valueOf(c.get(Calendar.HOUR));
            hc1="0"+hc;
        }else{
            hc1=String.valueOf(c.get(Calendar.HOUR));
        }
        if(c.get(Calendar.MINUTE)<10) {
            mc = String.valueOf(c.get(Calendar.MINUTE));
            mc1="0"+mc;
        }else{
            mc1=String.valueOf(c.get(Calendar.MINUTE));
        }
        String actualSis=hc1+":"+mc1;

        Cursor cursorDb=BaseDeDatos.rawQuery
                ("select nombreCita, fechaCita, horaCita, descripcionCita, direccionCita " +
                        "from cita " +
                        "where '"+actualSis+"'=horaCita", null);

        if(cursorDb.moveToFirst()) {
            do {
                //Cursor cursorbd=VerificarBD();
                String name = cursorDb.getString(0) + "";
                String fecha = cursorDb.getString(1)+ "";
                String hora = cursorDb.getString(2) + "";
                String descripcion = cursorDb.getString(3) + "";
                String direccion = cursorDb.getString(4) + "";

                data = "Nombre de la Cita" +name +
                        "\nA las:" + hora +
                        "\nEl:" + fecha +
                        "\nDescripción:" + descripcion +
                        "\nDirección:" + direccion ;

            }while (cursorDb.moveToNext());

        }
        BaseDeDatos.close();
        return data;
    }

    public static void setDetalleMed(String det){
        parametros=det;
    }

}

