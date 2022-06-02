package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IngresarFechaNac extends AppCompatActivity {
    private static EditText IntroFecha;
    private int dia, mes, ano;
    private String dia1,mes1;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    public Verificador nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_fecha_nac);

        dia = C.get(Calendar.DAY_OF_MONTH);
        mes = C.get(Calendar.MONTH);
        ano = C.get(Calendar.YEAR);

        IntroFecha = (EditText) findViewById(R.id.IntroFecha);

        IntroFecha.setOnClickListener((view) -> {

            showDialog(DATE_ID);
        });

    }
    //Metodo DE PONER LA fecha

    private void colocar_fecha() {
        IntroFecha.setText(dia1 + "/" + mes1 + "/" + ano + "");
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    dia = dayOfMonth;
                    if (dia < 10) {
                        dia1 = "0" + dayOfMonth;
                    } else {
                        dia1 = "" + dayOfMonth;
                    }
                    mes = monthOfYear;
                    if (mes < 10) {
                        mes1 = "0" + (monthOfYear + 1);
                    } else {
                        mes1 = "" + (monthOfYear + 1);
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
        }
        return null;
    }


    //Método Boton Siguiente
    public void Siguiente(View view) {
        nuevo = new Verificador();
        String ver = IngresarFechaNac.getText().toString();
        if (!ver.isEmpty()) {
            if(nuevo.fecPermitida(ver)) {
                Intent siguiente = new Intent(this, IngresarEnfermedad.class);
                startActivity(siguiente);
                finish();
            }else{
                IntroFecha.setError(nuevo.ErrorFechPermitida(ver));
            }
        } else
            IntroFecha.setError("ingrese fecha");

    }

    //Para la base de datos
    public static String getText(){
        String fech = IntroFecha.getText().toString();
        IntroFecha.setText("");
        return fech;
    }
    //Método para el botón Cancelar
    public void Bienvenido(View view){
        Intent bienvenido = new Intent(this,Inicio2.class);
        startActivity(bienvenido);
        finish();
    }


}