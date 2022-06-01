package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ListadoDeCitas extends AppCompatActivity {
    RecyclerView listaCita;
    ArrayList<Citas> listaCitasConsulta;
    AdminSQLite administrador=Inicio.BD();
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_de_citas);
        String fecha = CalendarioCitas.getFecha();
        //fecha= "19/05/2022";

        //text=(TextView)findViewById(R.id.prueba);

        //text.setText("  "+fecha);



        listaCita= findViewById(R.id.listaCitasCalendario);
        listaCita.setLayoutManager(new LinearLayoutManager(this));

        listaCitasConsulta= administrador.mostrarCitas(fecha);

        listaCitasAdapter adapter = new listaCitasAdapter(listaCitasConsulta);

        listaCita.setAdapter(adapter);
    }


}