package com.example.health4u;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class notiService extends Service {

    Timer time;
    Intent in;
    int fl;
    int sid;
    TareaAsync ta;
    OneTimeWorkRequest otwr;
    Calendar calendar=Medicamento3.calendar;
    Calendar actual=Medicamento3.actual;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "start");
        Noti();
        //otwr=new OneTimeWorkRequest.Builder(notiWork.class).build();
        //WorkManager.getInstance().enqueue(otwr);
        //onDestroy();
        //in=intent;
        //fl=flags;
        //sid=startId;

        //WorkRequest ow=new OneTimeWorkRequest.Builder(notiWork.class).build();
        //WorkManager.getInstance(this).enqueue(ow);

        //time = new Timer();
        //time.schedule(new TimerTask() {
          //  @Override
            //public void run() {
                /*Intent prueba=new Intent(notiService.this,pruebanoti.class);
                prueba.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(prueba);*/

                //oreo("Medicamento", data);
              //  onDestroy();
            //}
        //}, 60000);


            return notiService.START_NOT_STICKY;

    }
    @Override
    public void onDestroy() {
        Log.d("service", "destroy");

                //ta=new TareaAsync();
                //ta.execute();
        super.onDestroy();


    }
    private void Noti(){
        //calendar.set(Calendar.DAY_OF_MONTH,Medicamento2.getDiaMed());
        //calendar.set(Calendar.MONTH,Medicamento2.getMesMed());
        //calendar.set(Calendar.YEAR,Medicamento2.getAnioMed());
        Medicamento2.getFechaIni();
        Medicamento2.getFechaFin();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dtfD=DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter dtfM=DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter dtfY=DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter dtfh=DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter dtfm=DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter dtfs=DateTimeFormatter.ofPattern("ss");
            actual.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dtfD.format(LocalDateTime.now())));
            actual.set(Calendar.MONTH,Integer.parseInt(dtfM.format(LocalDateTime.now())));
            actual.set(Calendar.YEAR,Integer.parseInt(dtfY.format(LocalDateTime.now())));
            actual.set(Calendar.HOUR,Integer.parseInt(dtfh.format(LocalDateTime.now())));
            actual.set(Calendar.MINUTE,Integer.parseInt(dtfm.format(LocalDateTime.now())));
            actual.set(Calendar.SECOND,Integer.parseInt(dtfs.format(LocalDateTime.now())));
        }

        Long tiempo=calendar.getTimeInMillis()-actual.getTimeInMillis();
        //notiWork.guardarNoti(tiempo);
    }

    private void NotificarMed(String t, String d){
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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_IMMUTABLE);

        builder.setContentTitle(t)
                .setTicker("Nueva notificacion")
                .setContentIntent(pendingIntent)
                .setContentText(d)
                .setSmallIcon(R.drawable.ico_4u)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        Random random = new Random();
        int idNotify=random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());


        //doWork();

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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);



        builder.setContentTitle(t)
                .setTicker("Nueva notificacion")
                .setContentIntent(pendingIntent)
                .setContentText(d)
                .setSmallIcon(R.drawable.ico_4u)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        Random random = new Random();
        int idNotify=random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());


    }

    //public Cursor VerificarBD(){


      //  return cursorDb;
    //}
}
