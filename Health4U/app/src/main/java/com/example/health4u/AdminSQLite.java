package com.example.health4u;

import static com.example.health4u.Inicio.administrador;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


//Heredamos los metodos de la clase y reescribimos los metodos que heredamos
public class AdminSQLite  extends SQLiteOpenHelper {

    public AdminSQLite(@Nullable Inicio context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //Creo las tablas
        BaseDeDatos.execSQL("create table datosPerfil(nombrePerfil text,fechaDeNacimiento text, peso real, enfermedadBase text )");
        BaseDeDatos.execSQL("create table  medicamento(id Integer primary key autoincrement, nombreMedicamento text, fechaInicioMed text, fechaFinMed text, horaInicio text, periodicidad int, tiempo text, dosisMed text)");
        BaseDeDatos.execSQL("create table  cita(id Integer primary key autoincrement, nombreCita text, fechaCita text, horaCita text, descripcionCita text, direccionCita text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public ArrayList<Medicamentos> mostrarMedicamentos() {

        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();

        ArrayList<Medicamentos> listaMedicamentos = new ArrayList<>();
        Medicamentos medicamento = null;
        Cursor cursor = null;

        cursor = BaseDeDatos.rawQuery("select id,  nombreMedicamento, dosisMed , fechaInicioMed, fechaFinMed from medicamento ", null);

        if (cursor.moveToFirst()) {
            do {
                medicamento = new Medicamentos();
                medicamento.setId(cursor.getInt(0));
                medicamento.setNombre(cursor.getString(1));
                medicamento.setDosis(cursor.getString(2));
                medicamento.setFechaIni(cursor.getString(3));
                medicamento.setFechaFin(cursor.getString(4));
                listaMedicamentos.add(medicamento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaMedicamentos;


    }

    public ArrayList<Medicamentos> mostrarBuscados(String cadena) {


        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();

        ArrayList<Medicamentos> listaMedicamentos = new ArrayList<>();
        Medicamentos medicamento = null;
        Cursor cursor = null;

        cursor = BaseDeDatos.rawQuery("select id,  nombreMedicamento, dosisMed , fechaInicioMed, fechaFinMed from medicamento where nombreMedicamento like '%"+ cadena + "%'", null);
                //where nombreMedicamento like '%"+ cadena + "%'"

        if (cursor.moveToFirst()) {
            do {
                medicamento = new Medicamentos();
                medicamento.setId(cursor.getInt(0));
                medicamento.setNombre(cursor.getString(1));
                medicamento.setDosis(cursor.getString(2));
                medicamento.setFechaIni(cursor.getString(3));
                medicamento.setFechaFin(cursor.getString(4));
                listaMedicamentos.add(medicamento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaMedicamentos;
    }

    public void eliminarHist(){
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
        BaseDeDatos.execSQL("delete from medicamento" );

    }




}
