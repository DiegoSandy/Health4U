package com.example.health4u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Medicamento2 extends AppCompatActivity {

    private static EditText FechaIni, FechaFin;
    private static int dia,mes,ano;
    private static int d,m,a;
    private String dia1i,mes1i;
    private String dia1f,mes1f;
    static final int DATE_ID = 0;
    static final int DATE_IDD = 1;
    Calendar C = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento2);

        dia = C.get(Calendar.DAY_OF_MONTH);
        mes = C.get(Calendar.MONTH);
        ano = C.get(Calendar.YEAR);

        d = C.get(Calendar.DAY_OF_MONTH);
        m = C.get(Calendar.MONTH);
        a = C.get(Calendar.YEAR);

        FechaIni=(EditText)findViewById(R.id.textFechaIni);
        FechaFin=(EditText)findViewById(R.id.textFechaFin);

        FechaIni.setOnClickListener((view) -> {
            showDialog(DATE_ID);
        });
        FechaFin.setOnClickListener((view) -> {
            showDialog(DATE_IDD);
        });

    }
    //Metodo para colocar la fecha inicial
    private void colocar_fecha() {
        FechaIni.setText(dia1i + "/" + mes1i + "/" + ano + "");
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dia = dayOfMonth;
                    if (dia < 10) {
                        dia1i = "0" + dayOfMonth;
                    } else {
                        dia1i = "" + dayOfMonth;
                    }
                    mes = monthOfYear;
                    if (mes < 10) {
                        mes1i = "0" + (monthOfYear + 1);
                    } else {
                        mes1i = "" + (monthOfYear + 1);
                    }
                    ano = year;
                    colocar_fecha();
                }


            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, dia, mes, ano);
            case DATE_IDD:
                return new DatePickerDialog(this, mDateSetListener1, d, m, a);
        }
        return null;
    }


    //Metodo para colocar la fecha final
    private void colocar_fecha1() {
        FechaFin.setText(dia1f + "/" + mes1f + "/" + a + "");
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener1 =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    d = dayOfMonth;
                    if (d < 10) {
                        dia1f = "0" + dayOfMonth;
                    } else {
                        dia1f = "" + dayOfMonth;
                    }
                    mes = monthOfYear;
                    if (mes < 10) {
                        mes1f = "0" + (monthOfYear + 1);
                    } else {
                        mes1f = "" + (monthOfYear + 1);
                    }
                    a = year;
                    colocar_fecha1();
                }


            };



    public static String getFechaIni(){
        // setDiaMesAnio(FechaIni.getText().toString());
        String valor=FechaIni.getText().toString();
        FechaIni.setText("");
        return valor;
    }
    public static String getFechaFin(){
        String valor=FechaFin.getText().toString();
        FechaFin.setText("");
        return valor;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void next (View view){
        Verificador ver=new Verificador();
        String st=FechaIni.getText().toString();
        String st2=FechaFin.getText().toString();
        if(!st.isEmpty()){
            if(!st2.isEmpty()){
                if(ver.verFormato(st)){
                    if(ver.verFormato(st2)){
                        if(ver.verificadorFechaValida(st)){
                            if(ver.verificadorFechaValida(st2)){
                                if(ver.verificadorFechaCita(st)){
                                    if(ver.verificadorFechaCita(st2)){
                                        if(ver.verificadorFechaIniFin(st, st2)){
                                            Intent next=new Intent(this, Medicamento3.class);
                                            startActivity(next);
                                            finish();
                                        }else{
                                            //FechaIni.setError(ver.ErrorVerificadorFechaIniFin(st, st2));
                                            FechaFin.setError(ver.ErrorVerificadorFechaIniFin(st, st2));
                                        }
                                    }else{
                                        FechaFin.setError(ver.errorFechaCita(st2));
                                    }
                                }else{
                                    FechaIni.setError(ver.errorFechaCita(st));
                                }

                            }else{
                                FechaFin.setError(ver.DefinidorErrorFecha(st2));
                            }
                        }else{
                            FechaIni.setError(ver.DefinidorErrorFecha(st));
                        }
                    }else{
                        FechaFin.setError(ver.definidorErrorFormatoFecha(st2));
                    }
                }else{
                    FechaIni.setError(ver.definidorErrorFormatoFecha(st));
                }
            }else{
                FechaFin.setError("Ingrese fecha");
            }
        }else{
            FechaIni.setError("Ingrese Fecha");
        }

    }

    public void cancelar (View view){
        Intent cancelar=new Intent(this, Menu.class);
        startActivity(cancelar);
        finish();
    }

   /* private static void setDiaMesAnio(String F){
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

    public static int getDiaMed(){
        return d;
    }
    public static int getMesMed(){
        return m;
    }
    public static int getAnioMed(){
        return a;
    }

    */
}