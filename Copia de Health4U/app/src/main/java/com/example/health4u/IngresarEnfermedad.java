package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IngresarEnfermedad extends AppCompatActivity {
    public static EditText enfermedad;
    public static EditText peso;
    public Verificador nuevo;
    //AdminSQLite administrador = new AdminSQLite(this,"registro",null,1);
    AdminSQLite administrador=Inicio.BD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_enfermedad);
        enfermedad= (EditText) findViewById(R.id.enfBase);
        peso=(EditText) findViewById(R.id.kilos);
    }
    public void Guardar(View view){
        nuevo = new Verificador();
        String enfermedad1 = enfermedad.getText().toString();
        String peso1 =peso.getText().toString();
        if(nuevo.datosOpcionales(enfermedad1,20)){
            if(nuevo.verificarPeso(peso1)){
                //ANIADIR BASE DE DATOS Y DIRECCIONAR AL MENU
                String Nom = Ingresar_Nombre.getText();
                String Fech= IngresarFechaNac.getText();
                String Enf= getText1();
                String Pes= getText2();
                //Es el metodo que va a abrir nuestra BD para leer y escribir:
                SQLiteDatabase BaseDeDatos = administrador.getWritableDatabase();

                ContentValues datosPerfil = new ContentValues();

                datosPerfil.put("nombrePerfil", Nom);
                datosPerfil.put("fechaDeNacimiento", Fech);
                if(!Enf.isEmpty()){
                    datosPerfil.put("enfermedadBase",Enf);
                }else {
                    Enf="";
                    datosPerfil.put("enfermedadBase",Enf);
                }
                if(!Pes.isEmpty()){
                    datosPerfil.put("peso", Pes);
                }else{
                    Pes="";
                    datosPerfil.put("peso",Pes);
                }
                BaseDeDatos.insert("datosPerfil", null, datosPerfil );
                //Para cerrar la BD que tambien es importante
                BaseDeDatos.close();
                //Inicio2.setIniciar();
                Intent bienvenido = new Intent(this,Menu.class);
                startActivity(bienvenido);
                finish();
            }else{
                peso.setError(nuevo.definidorDelErrorPeso(peso1));
            }
        }else{
            enfermedad.setError(nuevo.ErrorDatosOpcionales(enfermedad1,20));
        }
    }
    //Para la base de datos
    public static String getText1(){
        String enf = enfermedad.getText().toString();
        enfermedad.setText("");
        return enf;
    }
    public static String getText2(){
        String pes = peso.getText().toString();
        peso.setText("");
        return pes;
    }
    //Método para el botón Cancelar
    public void Bienvenido(View view){
        Intent bienvenido = new Intent(this,Inicio2.class);
        startActivity(bienvenido);
        finish();
    }
}