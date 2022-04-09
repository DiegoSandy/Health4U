/*package com.example.health4u;
//Administrador de la base de datos

import static android.widget.Toast.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;


//Heredamos los metodos de la clase y reescribimos los metodos que heredamos
public class AdminSQLite  extends SQLiteOpenHelper{

    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Metodo para hacer en la clase de los botonesssss
    public void RegistrarPerfil (View view) {
        //Creamos un objeto para nuestra clase de base de datos
        //Donde dice registro es el nombre que le pusimos a nuestra BD
        AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
        //Es el metodo que va a abrir nuestra BD para leer y escribir:
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        String nombrePerfil = nombreDelCampoQueSelleno.getText().toString();
        String fechaDeNacimiento = nnnn.getText().toString();
        String enfermedadBase = nnnn.getText().toString();
        String peso = nnnn.getText().toString();
        //Para validar que si se llenaran los campos obligatorios, is Empty= Es diferente de vacio
        if (!nombrePerfil.isEmpty()) {
            ContentValues datosBD = new ContentValues();
            //Mandar los valor a la BD "nombre de la llave en la bd", la variables en la que guardamos el valor
            datosBD.put("nombrePerfil", nombrePerfil);
        }
        if (!fechaDeNacimiento.isEmpty()){
            datosBD.put("fechaDeNacimiento", fechaDeNacimiento);
        }
        if (!enfermedadBase.isEmpty()){
            datosBD.put("enfermedadBase",enfermedadBase);
        }
        if(!peso.isEmpty()) {
            datosBD.put("peso", nombrePerfil);
        }

           // Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
           BaseDeDatos.insert("datosPerfil", null, datosBD );
           //Para cerrar la BD que tambien es importante
            BaseDeDatos.close();
            //Para borrar los campos que llenaron
            nnnnn.setText("");//Asi con todos los campos
         else {

            Toast.makeText(this,"EL nombre y fecha de nacimiento son obligatorios", LENGTH_SHORT).show();

        }
    }
   //Aqui termina el metodo
   public void RegistrarMedicamento (View view) {
       //Creamos un objeto para nuestra clase de base de datos
       //Donde dice registro es el nombre que le pusimos a nuestra BD
       AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
       //Es el metodo que va a abrir nuestra BD para leer y escribir:
       SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
       String nombreMed = nombreDelCampoQueSelleno.getText().toString();
       String fechaInicioMed = nnnn.getText().toString();
       String fechaFinMed = nnnn.getText().toString();
       String horaInicio = nnnn.getText().toString();
       String periodicidad = nnnn.getText().toString();
       String dosis = nnnn.getText().toString();
       //Para validar que si se llenaran los campos obligatorios, is Empty= Es diferente de vacio
       if (!nombreMed.isEmpty() ) {
           ContentValues datosBD = new ContentValues();
           //Mandar los valor a la BD "nombre de la llave en la bd", la variables en la que guardamos el valor
           datosBD.put("nombreMedicamento", nombreMed);
       }
       if (!fechaInicioMed.isEmpty() ) {
           datosBD.put("fechaInicioMed", fechaInicioMed);
       }

       if(!fechaFinMed.isEmpty()) {
           datosBD.put("fechaFinMed", fechaFinMed);
       }
       if (!horaInicio.isEmpty()){
           datosBD.put("horaInicio",horaInicio);
       }
       if(!periodicidad.isEmpty()) {
           datosBD.put("periodicidad", periodicidad);
       }
       if(!dosis.isEmpty()) {
           datosBD.put("dosisMed", dosis);
       }
           // Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
           BaseDeDatos.insert("medicamento", null, datosBD );
           //Para cerrar la BD que tambien es importante
           BaseDeDatos.close();
           //Para borrar los campos que llenaron
           nnnnn.setText("");//Asi con todos los campos
        else {

           Toast.makeText(this,"EL nombre y fecha de nacimiento son obligatorios", LENGTH_SHORT).show();

       }
   }
    public void RegistrarCita (View view) {
        //Creamos un objeto para nuestra clase de base de datos
        //Donde dice registro es el nombre que le pusimos a nuestra BD
        AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
        //Es el metodo que va a abrir nuestra BD para leer y escribir:
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        String nombreCita = nombreDelCampoQueSelleno.getText().toString();
        String fechaCita = nnnn.getText().toString();
        String horaCita = nnnn.getText().toString();
        String descripcionCita = nnnn.getText().toString();
        String direccionCita = nnnn.getText().toString();
        //Para validar que si se llenaran los campos obligatorios, is Empty= Es diferente de vacio
        if (!nombreCita.isEmpty()) {
            ContentValues datosBD = new ContentValues();
            //Mandar los valor a la BD "nombre de la llave en la bd", la variables en la que guardamos el valor
            datosBD.put("nombreCita", nombreCita);
        }
        if (!fechaCita.isEmpty()){
            datosBD.put("fechaCita", fechaCita);
        }
        if (!horaCita.isEmpty()){
            datosBD.put("horaCita",horaCita);
        }
        if(!descripcionCita.isEmpty()) {
            datosBD.put("descripcionCita", descripcionCita);
        }
        else{
            datosBD.put("descripcionCita", null);
        }
        if(!direccionCita.isEmpty()) {
            datosBD.put("direccionCita", direccionCita);
        }
        else{
            datosBD.put("direccionCita", null);
        }

        // Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
        BaseDeDatos.insert("cita", null, datosBD );
        //Para cerrar la BD que tambien es importante
        BaseDeDatos.close();
        //Para borrar los campos que llenaron
        nnnnn.setText("");//Asi con todos los campos
     else {

        Toast.makeText(this,"EL nombre y fecha de nacimiento son obligatorios", LENGTH_SHORT).show();

        }
        }
    //Metodo para consultar un dato, esto nos puede servir para buscar un medicamento en el historial
    public void Buscar(View view){
        AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();

        String nombreMedicamentoBus = nombredelcampodondeselleno.getText().toString();
        //Validacion
        if(!nomMedicamentoBus.isEmpty()){
            //Cursor es la clase que nos va ayudar a seleccionar los datos que vamos a buscar en la BD
            //EL rawquery para hacer el select
            Cursor fila=BaseDeDatos.rawQuery
                    ("select nombreMedicamento, fechaInicioMed, fechaFinMed, dosisMed from medicamentos where nombreMedicamento ="+ nombreMedicamentoBus, null);
            //Revisar si nuestra consulta si tiene valores para luego mostrarlos
            if(fila.moveToFirst()){
                //Entender lo del verctor, m in8:09 https://www.youtube.com/watch?v=KAo5-ayChbs&t=269s
            }
        else{
            //Mostramos un mensaje de que no existe ese medicamento
            }
        }
    }


    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos){
     //Creo las tablas
       BaseDeDatos.execSQL("create table datosPerfil(nombrePerfil text,fechaDeNacimiento text, peso real, enfermedadBase text )");
       BaseDeDatos.execSQL("create table  medicamento(nombreMedicamento text, fechaInicioMed text, fechaFinMed text, horaInicio text, periodicidad int, tiempo text, dosisMed text)");
       BaseDeDatos.execSQL("create table  cita(nombreCita text, fechaCita text, horaCita text, descripcionCita text, direccionCita text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
*/

