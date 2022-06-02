package com.example.health4u;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.work.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class RebootServiceClass extends IntentService {
    Calendar actual=Calendar.getInstance();
    public RebootServiceClass(String name) {
        super(name);
        startForeground(1, new Notification());
    }

    public RebootServiceClass() {
        super("RebootServiceClass");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString("caller");
        if (intentType == null) return;
        if (intentType.equals("RebootReceiver")) {

            SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            //Utils.setAlarm(settings.getInt("alarmID", 0), settings.getLong("alarmTime", 0), this);
            Noti(settings.getString("nomCita", "nom_Cita"),settings.getLong("tiempo",0));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Noti(String nombre,Long time){

        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        DateTimeFormatter dtfD=DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dtfM=DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dtfY=DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dtfh=DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter dtfm=DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter dtfs=DateTimeFormatter.ofPattern("ss");



        LocalDateTime nuevo =LocalDateTime.now();
        actual.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dtfD.format(nuevo)));
        actual.set(Calendar.MONTH,Integer.parseInt(dtfM.format(nuevo)));
        actual.set(Calendar.YEAR,Integer.parseInt(dtfY.format(nuevo)));
        actual.set(Calendar.HOUR_OF_DAY,Integer.parseInt(dtfh.format(nuevo)));
        actual.set(Calendar.MINUTE,Integer.parseInt(dtfm.format(nuevo)));
        actual.set(Calendar.SECOND,Integer.parseInt(dtfs.format(nuevo)));
        //}

        Long tiempo=time-actual.getTimeInMillis();
        //Long tiempo=120000l;
        Data data=new Data.Builder()
                .putString("name",nombre)
                .putString("tipo","2").build();
        notiWork.guardarNoti(tiempo,data);
    }
}
