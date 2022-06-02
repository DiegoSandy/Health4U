package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class Medicamento3 extends AppCompatActivity {

    private  Spinner spinner1;
    private  EditText  periodicidad;//, hora, minuto;
    Button horaboton;
   // Button minboton;
    int h;
    int m;

    //private AdminSQLite administrador2 = new AdminSQLite(this, "registro", null, 1);
    AdminSQLite administrador=Inicio.BD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento3);

        horaboton=findViewById(R.id.horaboton);
       // minboton=findViewById(R.id.minboton);
        periodicidad=(EditText)findViewById(R.id.textPeriodicidad);
        //hora=(EditText)findViewById(R.id.editTextTime);
        //minuto=(EditText)findViewById(R.id.editTextTime2);
        spinner1 = (Spinner) findViewById(R.id.spinner);

        String[] opciones={"Horas", "Minutos", "Semanas", "Meses", "Dias"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_item_periodicidad, opciones);
        spinner1.setAdapter(adapter);
    }

   /* public static String getPeriodicidad(){
        String valor=periodicidad.getText().toString();
        periodicidad.setText("");
        return valor;
    }
    public static String gethora(){
        String valor=hora.getText().toString();
        hora.setText("");
        return valor;
    }
    public static String getMinuto(){
        String valor=minuto.getText().toString();
        minuto.setText("");
        return valor;
    }
    public static String getIntervalo(){
        String valor=spinner1.getSelectedItem().toString();

        return valor;
    }*/

    public void ElegirHora(View view){
        TimePickerDialog.OnTimeSetListener sethora= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hora, int min) {
                h=hora;
                m=min;
                horaboton.setText(String.format(Locale.getDefault(),"%02d:%02d",h,m));
            }
        };
        int style= AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog= new TimePickerDialog(Medicamento3.this,style, sethora, h, m, true );
        timePickerDialog.setTitle("Hora Inicio");
        timePickerDialog.show();

    }


    public void Guardar (View view){
        String NombreMedicamento=Medicamento1.getMedicamento();
        String Dosis=Medicamento1.getDosis();
        String FechaIni=Medicamento2.getFechaIni();
        String FechaFin=Medicamento2.getFechaFin();

        String p=periodicidad.getText().toString();
        String horaIni=horaboton.getText().toString();
        String i=spinner1.getSelectedItem().toString();
        if(!p.isEmpty()){

            SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
            ContentValues datosMedicamento = new ContentValues();
            datosMedicamento.put("nombreMedicamento", NombreMedicamento);
            datosMedicamento.put("dosisMed", Dosis);
            datosMedicamento.put("fechaInicioMed", FechaIni);
            datosMedicamento.put("fechaFinMed", FechaFin);
            datosMedicamento.put("horaInicio", horaIni);
            datosMedicamento.put("periodicidad", p+""+i);
            // Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
            BaseDeDatos.insert("medicamento", null, datosMedicamento );
            //Para cerrar la BD que tambien es importante
            BaseDeDatos.close();
            periodicidad.setText("");
            //hora.setText("");
            //minuto.setText("");
            horaboton.setText("");
            Intent sig=new Intent(this, Animacion.class);
            startActivity(sig);
            finish();

        }else{
            periodicidad.setError("Ingrese Periodicidad");
        }

    }
    public void cancelar (View view){
        Intent cancelar=new Intent(this, Menu.class);
        startActivity(cancelar);
        finish();
    }

}