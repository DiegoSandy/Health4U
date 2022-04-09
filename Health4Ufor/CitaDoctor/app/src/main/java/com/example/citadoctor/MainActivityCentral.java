package com.example.citadoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityCentral extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_central);
    }

    public void CITA(View v) {
        Intent pcita = new Intent(this, MainActivity.class);
        startActivity(pcita);
    }
}