package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Medicamento1 extends AppCompatActivity {

    private static EditText medicamento;
    private static EditText dosis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento1);

        medicamento=(EditText)findViewById(R.id.textmedicamento);
        dosis=(EditText)findViewById(R.id.textdosis);

    }

    public static String getMedicamento(){
        String valor=medicamento.getText().toString();
        medicamento.setText("");
        return valor;
    }
    public static String getDosis(){
        String valor=dosis.getText().toString();
        dosis.setText("");
        return valor;
    }


    public void next (View view){
        Verificador ver=new Verificador();
        String st=medicamento.getText().toString();
        String st2=dosis.getText().toString();
        if(ver.maxCaracteres(st, 25)){
            if(ver.verificardosis(st2)){
                //dosis.setText("Sin Especificar");
                siguiente();
            }else{
                dosis.setError(ver.definidorDelErrordosis(st2));
                //siguiente();
            }
        }else{
            medicamento.setError(ver.ErrorDatosNoOpcionales(st,25));
        }

    }
    public void siguiente(){
        Intent next=new Intent(this, Medicamento2.class);
        startActivity(next);
        finish();
    }

    public void cancelar (View view){
        Intent cancelar=new Intent(this, Menu.class);
        startActivity(cancelar);
        finish();
    }
}