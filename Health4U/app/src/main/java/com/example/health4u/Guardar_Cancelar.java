/*package com.example.health4u;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class Guardar_Cancelar {
<<<<<<< HEAD

    private String NombreMedicamento=Medicamento1.getMedicamento();
    private String Dosis=Medicamento1.getDosis();
    private String FechaIni=Medicamento2.getFechaIni();
    private String FechaFin=Medicamento2.getFechaFin();
   private String Periodicidad=Medicamento3.getPeriodicidad();
    private String Intervalo=Medicamento3.getIntervalo();
    private String Hora=Medicamento3.gethora();
    private String Minuto=Medicamento3.getMinuto();


    private static ContentValues DatosMedicamento;
=======
    private static ContentValues DatosCita;
>>>>>>> 98a5e2fe45e4dd30b1d3e87698773f3b879bc014
    //Clase AdminSqlite en parte de Gabii
    AdminSQLite administrador = new AdminSQLite(this, "registro", null, 1);

    private static void SetAll(ContentValues Datos){
<<<<<<< HEAD
        DatosMedicamento=Datos;
    }

    public static void Guardar(){
=======
        DatosCita=Datos;
    }

    private static void Guardar(){
>>>>>>> 98a5e2fe45e4dd30b1d3e87698773f3b879bc014
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
