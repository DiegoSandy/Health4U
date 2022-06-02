package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class HistorialBus extends AppCompatActivity {
    RecyclerView listaBusqueda;
    ArrayList<Medicamentos> listaMedicamentosBuscados;
    AdminSQLite administrador=Inicio.BD();
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_bus);
        String med = HistorialPrin.getText();
        HistorialPrin.setText();

        text=(TextView)findViewById(R.id.medicamentoB);

        text.setText("  "+med);


        listaBusqueda= findViewById(R.id.listaBusquedas);
        listaBusqueda.setLayoutManager(new LinearLayoutManager(this));

        listaMedicamentosBuscados= administrador.mostrarBuscados(med);

        listaMedicamentosAdapter adapter = new listaMedicamentosAdapter(listaMedicamentosBuscados);

        listaBusqueda.setAdapter(adapter);
    }
    public void ATRAS (View v){
        Intent historial = new Intent (this,HistorialPrin.class );
        startActivity(historial);
        finish();
    }
}