package com.example.health4u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.UFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.animation.ChildrenAlphaProperty;

import java.text.Format;
import java.util.Calendar;
import java.util.Locale;

public class ProgramarCita2 extends AppCompatActivity {
    static Button boton; //boton para elegir hora
    TextView text;
    static int hora;
    static int minuto;
    private static EditText bt_fecha; //se cambio el nombre de la variable fechacita a textView4
    private int dia,mes,ano;
    private String dia1,mes1;
    static final int DATE_ID=0;
    public Verificador nuevo;
    Calendar C=Calendar.getInstance();
    static Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        boton = findViewById(R.id.bHora);
        boton.setText("HR:MIN");
        //fechaCita = (EditText) findViewById(R.id.bt_fecha);
        //text=(TextView)findViewById(R.id.textView15);
        dia=C.get(Calendar.DAY_OF_MONTH);
        mes=C.get(Calendar.MONTH);
        ano=C.get(Calendar.YEAR);
        bt_fecha = (EditText) findViewById(R.id.bt_fecha);

        bt_fecha.setOnClickListener((view)->{
            showDialog(DATE_ID);
        });
    }
    //Metodo para poner la fecha
    private void colocar_fecha(){
        bt_fecha.setText(dia1 + "/" + mes1 + "/" + ano + "");
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dia = dayOfMonth;
                    if(dia<10){
                        dia1="0"+dayOfMonth;
                    }
                    else{
                        dia1=""+dayOfMonth;
                    }
                    mes = monthOfYear;
                    if(mes<10){
                        mes1="0"+(monthOfYear+1);
                    }
                    else{
                        mes1=""+(monthOfYear+1);
                    }
                    ano = year;
                    colocar_fecha();
                }
            };
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, dia, mes, ano);
        }
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Siguiente(View v) {
        nuevo = new Verificador();
        String horaCita = boton.getText().toString();
        String fechaProgramada = bt_fecha.getText().toString();
        if (!fechaProgramada.isEmpty()){
            if (nuevo.verFormato(fechaProgramada)){
                if (nuevo.verificadorFechaValida(fechaProgramada)){
                    if (nuevo.verificadorFechaCita(fechaProgramada)){
                        if(nuevo.verificarHora(horaCita)){
                            if (nuevo.verificarHoraValidaCita(horaCita,fechaProgramada)){
                                Intent next = new Intent(this, ProgramarCita3.class);
                                startActivity(next);
                                finish();
                            }else{
                                /*fechaCita.setError(nuevo.ErrorVerificadorHoraCita(horaCita,fechaProgramada));*/
                                text.setText("Hora Invalida");
                                text.setError("");
                            }
                        }else {
                            text.setText("Ingrese hora");
                            text.setError("");
                        }
                    }else{
                        bt_fecha.setError(nuevo.errorFechaCita(fechaProgramada));
                    }
                }else{
                    bt_fecha.setError(nuevo.DefinidorErrorFecha(fechaProgramada));
                }
            }else{
                bt_fecha.setError(nuevo.definidorErrorFormatoFecha(fechaProgramada));
            }
        }else{
            bt_fecha.setError("ingrese fecha");
        }


    }

    public void Cancelar(View v) {
        Intent cancel = new Intent(this, Menu.class);
        startActivity(cancel);
        finish();
    }
    public void time(View view){  //metodo para mostrar la lista en hora pero no se almacena en nigun lado
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                hora = i;
                minuto = i1;
                calendar.set(Calendar.HOUR,hora);
                calendar.set(Calendar.MINUTE,minuto);
                calendar.set(Calendar.SECOND,0);
                boton.setText(String.format(Locale.getDefault(),"%02d:%02d",hora,minuto));
            }
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,style,onTimeSetListener,hora,minuto,true);
        timePickerDialog.setTitle("Seleccionar Hora");
        timePickerDialog.show();

    }

    public static String getHora(){
        String HORA=boton.getText().toString();
        return HORA;
    }
    public static String getText(){
        //setDiaMesAnio(fechaCita.getText().toString());
        //calendar.set(Calendar.DAY_OF_MONTH,d);
        //calendar.set(Calendar.MONTH,m);
        //calendar.set(Calendar.YEAR,a);
        String a = bt_fecha.getText().toString();
        bt_fecha.setText("");
        return a;
    }
    /*private static void setDiaMesAnio(String F){
        String dia="";
        String mes="";
        String anio="";
        for(int i=0;i<F.length();i++){
            if(F.charAt(i)!='/') {
                if (i < 2) {
                    dia = dia + F.charAt(i);
                } else if (i < 5) {
                    mes = mes + F.charAt(i);
                }else{
                    anio=anio+F.charAt(i);
                }
            }
        }
        d=Integer.valueOf(dia);
        m=Integer.valueOf(mes);
        a=Integer.valueOf(anio);
    }

     */
}