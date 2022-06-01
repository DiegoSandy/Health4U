package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class detallNotifCita extends AppCompatActivity {
    private TextView tx2;
    private String informacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall_notif_cita);
        tx2 = (TextView) findViewById(R.id.tvNotifCita);
        //Bundle recibe = getIntent().getExtras();
        //infor = recibe.getString("keyDatos");
        tx2.setText(ProgramarCita3.data);
    }

    /*public void ImagSalir(View view){
        Intent salir = new Intent(Intent.ACTION_MAIN);
        salir.addCategory(Intent.CATEGORY_HOME);
        salir.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(salir);
    }


     */
}