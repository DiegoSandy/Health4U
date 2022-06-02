/*package com.example.health4u;

import static com.example.health4u.Inicio.administrador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BdConsultas extends AdminSQLite {

    public BdConsultas(@Nullable Inicio context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

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

        cursor = BaseDeDatos.rawQuery("select id,  nombreMedicamento, dosisMed , fechaInicioMed, fechaFinMed from medicamento where nombre like %"+ cadena + "%", null);

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
}*/
