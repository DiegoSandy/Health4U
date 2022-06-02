package com.example.health4u;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ProgramarCita3 extends AppCompatActivity {
    public static EditText descripcion;
    public static EditText direccion;
    public Verificador nuevo;
    public static String data;
    //AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
    AdminSQLite administrador=Inicio.BD();
    Calendar calendar=Calendar.getInstance();
    Calendar actual=ProgramarCita2.calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        descripcion = (EditText) findViewById(R.id.bt_descr);
        direccion = (EditText) findViewById(R.id.bt_dir);
    }

    public void Cancelar(View view) {
        nuevo = new Verificador();
        Intent cancel = new Intent(this, Menu.class);
        startActivity(cancel);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Guardar(View view){
        nuevo = new Verificador();
        String texto1 = descripcion.getText().toString();
        String texto2 = direccion.getText().toString();
        if(nuevo.datosOpcionales(texto1,35)){
            if(nuevo.datosOpcionales(texto2,35)){
                String nom_Cita = ProgramarCita.getText();
                String date = ProgramarCita2.getText();
                String des = getText();
                String dir = getText2();
                String hora=ProgramarCita2.getHora();
                //Es el metodo que va a abrir nuestra BD para leer y escribir:
                SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
                ContentValues datosCita = new ContentValues();
                datosCita.put("horaCita", hora);
                datosCita.put("nombreCita", nom_Cita);
                datosCita.put("fechaCita", date);

                calendar.set(Calendar.DAY_OF_MONTH,dia(date));
                calendar.set(Calendar.MONTH,mes(date));
                calendar.set(Calendar.YEAR,anio(date));
                calendar.set(Calendar.HOUR_OF_DAY,hr(hora));
                calendar.set(Calendar.MINUTE,min(hora));
                calendar.set(Calendar.SECOND,0);

                if(!des.isEmpty()) {
                    datosCita.put("descripcionCita", des);
                }
                else{     des="";
                    datosCita.put("descripcionCita", des);
                }if(!dir.isEmpty()) {
                    datosCita.put("direccionCita", dir);
                }
                else{ dir="";
                    datosCita.put("direccionCita", dir);
                }
// Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
                BaseDeDatos.insert("cita", null, datosCita );
                //Para cerrar la BD que tambien es importante
                data = "Nombre de la Cita: " +nom_Cita+
                        "\nA las: " +hora+
                        "\nEl: " +date+
                        "\nDescripción: " +des+
                        "\nDirección: " +dir;
                BaseDeDatos.close();
                //calendar=ProgramarCita2.calendar;
                Noti(nom_Cita);
                Intent siguiente = new Intent(this, Animacion.class);
                startActivity(siguiente);
                finish();
                //info = getcita();

            }else{
                direccion.setError(nuevo.ErrorDatosOpcionales(texto2, 35));
            }
        }else{
            descripcion.setError(nuevo.ErrorDatosOpcionales(texto1,35));
        }

    }

    public static String getText(){
        String a = descripcion.getText().toString();
        descripcion.setText("");
        return a;
    }

    public static String getText2(){
        String b = direccion.getText().toString();
        direccion.setText("");
        return b;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Noti(String nombre){

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

       Long tiempo=calendar.getTimeInMillis()-actual.getTimeInMillis();
        //Long tiempo=120000l;
        Data data=new Data.Builder()
                .putString("name",nombre)
                .putString("tipo","2").build();
        notiWork.guardarNoti(tiempo,data);
    }
    public int hr(String hora){
        String hrs = hora.substring(0,2);
        int hr = Integer.parseInt(hrs);
        return hr;
    }
    public int min(String hora){
        String min = hora.substring(3,5);
        int mins = Integer.parseInt(min);
        return mins;
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

}

