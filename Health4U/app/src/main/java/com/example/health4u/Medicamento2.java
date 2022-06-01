package com.example.health4u;

<<<<<<< HEAD
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Medicamento2 extends AppCompatActivity {

    private static EditText FechaIni, FechaFin;
    private static int d,m,a;

=======
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Medicamento2 extends AppCompatActivity {

>>>>>>> 98a5e2fe45e4dd30b1d3e87698773f3b879bc014
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento2);
<<<<<<< HEAD

        FechaIni=(EditText)findViewById(R.id.textFechaIni);
        FechaFin=(EditText)findViewById(R.id.textFechaFin);

    }

    public static String getFechaIni(){
        setDiaMesAnio(FechaIni.getText().toString());
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

    private static void setDiaMesAnio(String F){
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
=======
    }

    public void next (View view){
        Intent next=new Intent(this, Medicamento3.class);
        startActivity(next);
    }


>>>>>>> 98a5e2fe45e4dd30b1d3e87698773f3b879bc014
}