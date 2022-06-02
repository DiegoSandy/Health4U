package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProgramarCita extends AppCompatActivity {
    public static EditText nomCita;
    public Verificador nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomCita = (EditText) findViewById(R.id.txt_nombre);
    }

    public void Siguiente(View v) {
        nuevo = new Verificador();
        String ver = nomCita.getText().toString();
        if(nuevo.maxCaracteres(ver,25)){
            Intent next = new Intent(this, ProgramarCita2.class);
            startActivity(next);
            finish();
        }else{
            nomCita.setError(nuevo.ErrorDatosNoOpcionales(ver,25));
        }
    }

    public void Cancelar(View v) {
        Intent cancel = new Intent(this, Menu.class);
        startActivity(cancel);
        finish();
    }

    public static String getText(){
        String a = nomCita.getText().toString();
        nomCita.setText("");
         return a;
    }

}