package com.example.health4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

public class CalendarioCitas extends AppCompatActivity implements CalendarView.OnDateChangeListener {
    CalendarView simpleCalendarView;
    static String fecha;

    //android:showWeekNumber="true"
    //
    //        android:weekNumberColor="#406882"
    //        android:weekSeparatorLineColor="#6998ab"
    //        android:focusedMonthDateColor="#000000"
    //        android:unfocusedMonthDateColor="#747999"
    //
    //        android:minDate="01/05/2022"
    //        android:maxDate="30/12/2022"

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_citas);

        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setShowWeekNumber(true);
        simpleCalendarView.setWeekNumberColor(406882);
        simpleCalendarView.setWeekSeparatorLineColor(699889);
        simpleCalendarView.setFocusedMonthDateColor(000000);
        simpleCalendarView.setUnfocusedMonthDateColor(747999);

        simpleCalendarView.setOnDateChangeListener(this);

    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
        fecha=null;
        String dia;
        String mes;
        String anio;
        if(i>2000) {
            if (i2 < 10) {
                dia = "0" + Integer.toString(i2);
            } else {
                dia = Integer.toString(i2);
            }

            if ((i1 + 1) < 10) {
                mes = "0" + Integer.toString(i1 + 1);
            } else {
                mes = Integer.toString(i1 + 1);
            }

            anio = Integer.toString(i);
            fecha = dia + "/" + mes + "/" + anio;

        }
        else{
            if (i < 10) {
                dia = "0" + Integer.toString(i);
            } else {
                dia = Integer.toString(i);
            }

            if ((i1 + 1) < 10) {
                mes = "0" + Integer.toString(i1 + 1);
            } else {
                mes = Integer.toString(i1 + 1);
            }

            anio = Integer.toString(i2);
            fecha = dia + "/" + mes + "/" + anio;

        }

        Intent listaCi = new Intent (this,ListadoDeCitas.class );
        startActivity(listaCi);
        finish();

    }
    public static String getFecha(){
        return fecha;
    }

}