package com.example.health4u;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class RecordarCita extends AppCompatActivity {

     private Button boton1;
        NotificationCompat.Builder notificacion;
        private static final int idUnica=12;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            boton1=(Button) findViewById(R.id.boton1);

            notificacion=new NotificationCompat.Builder(this);
            notificacion.setAutoCancel(true);

            boton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    notificacion.setSmallIcon(R.mipmap.ic_launcher);
                    notificacion.setTicker("La alarma se pospondra 10 minutos");
                    notificacion.setWhen(System.currentTimeMillis());
                    notificacion.setContentTitle("DENTRO DE 10 MINUTOS");
                    notificacion.setContentText("Te volveremos a recordar la cita programada que tienes para hoy");

                    Intent intent=new Intent(Recordar.this,Recordar.class); //aqui se pasa a otra clase al hacer clic

                    PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    notificacion.setContentIntent(pendingIntent);

                    NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(idUnica,notificacion.build());
                }
            });
        }
    }

