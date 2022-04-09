package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Medicamento3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento3);
    }

    public void next (View view){
        Intent next=new Intent(this, Animacion.class);
        startActivity(next);
    }
}