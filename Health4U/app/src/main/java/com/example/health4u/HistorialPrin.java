package com.example.health4u;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class HistorialPrin extends AppCompatActivity {
    public static EditText medi;
    private Button mAlertbtn;


    RecyclerView listaMedicamentos;
    ArrayList<Medicamentos> listaArrayMedicamentos;
    AdminSQLite administrador=Inicio.BD();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_prin);
        medi = (EditText) findViewById(R.id.medicamentoBuscar);

        listaMedicamentos= findViewById(R.id.listaMedicamentos);
        listaMedicamentos.setLayoutManager(new LinearLayoutManager(this));


        listaArrayMedicamentos= administrador.mostrarMedicamentos();

        listaMedicamentosAdapter adapter = new listaMedicamentosAdapter(listaArrayMedicamentos);

        listaMedicamentos.setAdapter(adapter);

        mAlertbtn = (Button) findViewById(R.id.button10);
        mAlertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tam=listaArrayMedicamentos.size();
                if(tam!=0) {
                    AlertDialog.Builder alertaNoVacio = new AlertDialog.Builder(HistorialPrin.this);
                    alertaNoVacio.setMessage("¿Quiere borrar todo el historial?")
                            .setCancelable(false)
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int wich) {
                                    administrador.eliminarHist();
                                    Intent hisReca = new Intent(HistorialPrin.this, HistorialPrin.class);
                                    startActivity(hisReca);
                                    finish();

                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int wich) {

                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = alertaNoVacio.create();
                    titulo.setTitle("Confirmación");
                    titulo.show();
                }
                else if(listaArrayMedicamentos.isEmpty()){

                   AlertDialog.Builder alertavacio = new AlertDialog.Builder(HistorialPrin.this);
                   alertavacio.setMessage("El historial se encuentra vacío")
                   .setCancelable(false)
                           .setNegativeButton("ACEPTAR", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int wich) {
                                   Intent menu = new Intent(HistorialPrin.this, Menu.class);
                                   startActivity(menu);
                                   dialog.cancel();
                               }
                           });
                    AlertDialog titulo = alertavacio.create();
                    titulo.setTitle("");
                    titulo.show();


               }
            }
        });





    }


    public void BUSCAR (View v){
        String med= getText();

        Verificador ver=new Verificador();
        if(ver.maxCaracteres(med, 15)){

                Intent histBuscar = new Intent (this,HistorialBus.class );
                startActivity(histBuscar);
                finish();


        }else{
            medi.setError(ver.ErrorDatosNoOpcionales(med,15));
        }
    }
    public static String getText(){
        String Med = medi.getText().toString();

        return Med;
    }
    public static void setText(){
        medi.setText("");
    }
    }
