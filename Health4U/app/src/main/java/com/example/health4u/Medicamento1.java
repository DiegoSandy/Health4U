package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Medicamento1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento1);
    }

    public void next (View view){
        Intent next=new Intent(this, Medicamento2.class);
        startActivity(next);
    }
}