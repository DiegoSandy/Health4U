package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    //AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
    AdminSQLite administrador=Inicio.BD();
    Calendar calendar=Calendar.getInstance();
    Calendar actual=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        descripcion = (EditText) findViewById(R.id.bt_descr);
        direccion = (EditText) findViewById(R.id.bt_dir);
    }

    public void Cancelar(View view) {
        nuevo = new Verificador();
        String texto1 = descripcion.getText().toString();
        String texto2 = direccion.getText().toString();
        Intent cancel = new Intent(this, Menu.class);
        startActivity(cancel);
        finish();
    }

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
                BaseDeDatos.close();
                calendar=ProgramarCita2.calendar;
                Noti(nom_Cita);
                Intent siguiente = new Intent(this, Animacion.class);
                startActivity(siguiente);
                finish();
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

    private void Noti(String nombre){

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
        }

        Long tiempo=calendar.getTimeInMillis()-actual.getTimeInMillis();
        Data data=new Data.Builder()
                .putString("name",nombre)
                .putString("tipo","2").build();
        notiWork.guardarNoti(tiempo,data);
    }

}