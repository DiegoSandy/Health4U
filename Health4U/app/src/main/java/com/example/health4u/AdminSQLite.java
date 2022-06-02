package com.example.health4u;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;




//Heredamos los metodos de la clase y reescribimos los metodos que heredamos
public class AdminSQLite  extends SQLiteOpenHelper {

    public AdminSQLite(@Nullable Inicio context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //Creo las tablas
        BaseDeDatos.execSQL("create table datosPerfil(nombrePerfil text,fechaDeNacimiento text, peso real, enfermedadBase text )");
        BaseDeDatos.execSQL("create table  medicamento(nombreMedicamento text, fechaInicioMed text, fechaFinMed text, horaInicio text, periodicidad int, tiempo text, dosisMed text)");
        BaseDeDatos.execSQL("create table  cita(nombreCita text, fechaCita text, horaCita text, descripcionCita text, direccionCita text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
