package com.example.health4u;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Medicamentos {
    private int id;
    private String nombre;
    private String dosis;
    private String fechaIni;
    private String fechaFin;

    public Medicamentos() {
       /* this.id = id;
       int id, String nombre, String dosis, String fechaIni, String fechaFin
        this.nombre=nombre;
       this.dosis=dosis;
        this.fechaIni=fechaIni;
        this.fechaFin=fechaFin;

        */
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }




}
