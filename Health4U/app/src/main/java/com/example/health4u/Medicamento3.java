package com.example.health4u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Medicamento3 extends AppCompatActivity {
    TextView text;
    static String data;
    private  Spinner spinner1;
    private  EditText  periodicidad;//, hora, minuto;
    Button horaboton;
   // Button minboton;
     int h;
    int m;
    String hor,min;
    public static Calendar calendar=Calendar.getInstance();
    public static Calendar actual=Calendar.getInstance();
    static Long TIEMPO_PERIODO;
    static String Name;
    //private AdminSQLite administrador2 = new AdminSQLite(this, "registro", null, 1);
    AdminSQLite administrador=Inicio.BD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento3);



        horaboton=findViewById(R.id.horaboton);
        horaboton.setText("HR:MIN");
       // minboton=findViewById(R.id.minboton);
        periodicidad=(EditText)findViewById(R.id.textPeriodicidad);
        //hora=(EditText)findViewById(R.id.editTextTime);
        //minuto=(EditText)findViewById(R.id.editTextTime2);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        text=(TextView)findViewById(R.id.textView14);

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
                hor=String.format(Locale.getDefault(),"%02d:%02d",h,m);
            }
        };
        int style= AlertDialog.THEME_HOLO_LIGHT;
        TimePickerDialog timePickerDialog= new TimePickerDialog(Medicamento3.this,style, sethora, h, m, true );
        timePickerDialog.setTitle("Hora Inicio");
        timePickerDialog.show();

    }


    public void Guardar (View view){
        Verificador ver=new Verificador();
        String NombreMedicamento=Medicamento1.getMedicamento();
        String Dosis=Medicamento1.getDosis();
        String FechaIni=Medicamento2.getFechaIni();
        String FechaFin=Medicamento2.getFechaFin();

        String p=periodicidad.getText().toString();
        String horaIni=horaboton.getText().toString();
        String i=spinner1.getSelectedItem().toString();
        if(!p.isEmpty()){
            if(ver.periodo(p)){
                if(ver.verificarHora(horaIni)) {

                    SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
                    ContentValues datosMedicamento = new ContentValues();
                    datosMedicamento.put("nombreMedicamento", NombreMedicamento);
                    datosMedicamento.put("dosisMed", Dosis);
                    datosMedicamento.put("fechaInicioMed", FechaIni);
                    datosMedicamento.put("fechaFinMed", FechaFin);
                    datosMedicamento.put("horaInicio", horaIni);
                    datosMedicamento.put("periodicidad", p + "" + i);
                    Name = NombreMedicamento;
                    TIEMPO_PERIODO = calcularPeriodicidad(parse(p),i);
                    calendar.set(Calendar.HOUR,h);
                    calendar.set(Calendar.MINUTE,m);
                    calendar.set(Calendar.SECOND,0);
                    calendar.set(Calendar.DAY_OF_MONTH,dia(FechaIni));
                    calendar.set(Calendar.MONTH,mes(FechaIni));
                    calendar.set(Calendar.YEAR,anio(FechaIni));
                    // Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
                    BaseDeDatos.insert("medicamento", null, datosMedicamento);
                    //Para cerrar la BD que tambien es importante
                    data = "Nombre de Medicamento: " +NombreMedicamento +
                            "\nDosis: " + Dosis +
                            "\nFecha Inicio: " + FechaIni +
                            "\nFecha Fin: " + FechaFin;

                    BaseDeDatos.close();
                    //Intent intentService=new Intent(this, notiService.class);
                    //startService(intentService);
                    Noti(NombreMedicamento,Dosis,FechaIni,FechaFin,horaIni);
                    periodicidad.setText("");
                    //hora.setText("");
                    //minuto.setText("");
                    horaboton.setText("");
                    Intent sig = new Intent(this, Animacion.class);
                    startActivity(sig);
                    finish();
                }
                else{
                    text.setText("Ingrese hora");
                    text.setError("");
                }
            }else{
                periodicidad.setError(ver.ErrorPeriodo(p));
            }


        }else{
            periodicidad.setError("Ingrese Periodicidad");
        }

    }
    public void cancelar (View view){
        Intent cancelar=new Intent(this, Menu.class);
        startActivity(cancelar);
        finish();
    }
   // @RequiresApi(api = Build.VERSION_CODES.O)
    private void Noti(String nombre, String dosis, String fechaIni, String fechaFin,String h){
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
            Long tiempo=calendar.getTimeInMillis()-actual.getTimeInMillis();
            Data data=new Data.Builder()
                    //.putString("hora",h)
                    .putString("name",nombre)
                    .putString("tipo","1").build();
            notiWork.guardarNoti(tiempo,data);
        }


    }
    public int dia(String fecha){
        String dia = fecha.substring(0,2);
        int dias = Integer.parseInt(dia);
        return dias;
    }
    public int mes(String fecha){
        String mes = fecha.substring(3,5);
        int mese = Integer.parseInt(mes);
        return mese;
    }
    public int anio(String fecha){
        String anio = fecha.substring(6,10);
        int anios = Integer.parseInt(anio);
        return anios;
    }
    private int parse(String n){
        int periodo = Integer.parseInt(n);
        return periodo;
    }

    public Long calcularPeriodicidad(int n, String periodo){
        Long intervaloTime = 0l;
        if(periodo.equals("Horas")){
            intervaloTime = n *3600000l;
        }else{
            if(periodo.equals("Minutos")){
                intervaloTime = n *60000l;
            }else{
                if(periodo.equals("Semanas")){
                    intervaloTime = n *604800000l;
                }else{
                    if(periodo.equals("Meses")){
                        intervaloTime = n *262800000l;
                    }else{
                        if(periodo.equals("Dias")){
                            intervaloTime = n *86400000l;
                        }else{

                        }
                    }
                }
            }
        }
        return intervaloTime;
    }
}