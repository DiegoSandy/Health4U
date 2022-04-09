/*package com.example.health4u;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class Guardar_Cancelar {
    private static ContentValues DatosCita;
    //Clase AdminSqlite en parte de Gabii
    AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);

    private static void SetAll(ContentValues Datos){
        DatosCita=Datos;
    }

    private static void Guardar(){
        SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();
// Para guardar los valores dentro de la tabla de la BD, el ultimo parametro es del objeto que guardo los datos
        BaseDeDatos.insert("cita", null, DatosCita);
        //Para cerrar la BD que tambien es importante
        BaseDeDatos.close();
        DatosCita.clear();
    }

    private static void Cancelar(){
        DatosCita.clear();
    }
}*/
