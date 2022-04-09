package com.example.health4u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.view.View;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker) findViewById(R.id.TimePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }


    //OnToggleClicked() el metodo es implementar la funcionalidad del tiempo
    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();

            //se llama al calendario para obtener la hora actual en hrs y minutos
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            //usando la instanciada, tengo la clase AlarmReceiver class que hereda
            //BroadCastReceiver
            Intent intent = new Intent(this, AlarmReceiver.class);

            //llamamos a la transmision usando la intencion pendiente
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                //setting time as AM and PM
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }
            //la Alarma suena continuamente hasta que se apague el boton de alternar
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
            //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(time*1000),pending intent;
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }

    public class AlarmReceiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        //implementando el metodo onReceive()
        public void onReceive(Context context, Intent intent) {
            //we will use vibrator first
            Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            vibrator.VIBRATE(4000);

            Toast.makeText(context, "TIENES CITA", Toast.LENGTH_LONG).show();
            Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            if (alarmUri == null) {
                alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
            //setting default ringtone
            Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
            //play ringtone
            ringtone.play();
        }
    }
}




