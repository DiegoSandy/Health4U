package com.example.citadoctor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Siguiente(View v) {
        Intent next = new Intent(this, MainActivity2.class);
        startActivity(next);
    }

    public void Cancelar(View v) {
        Intent cancel = new Intent(this, MainActivityCentral.class);
        startActivity(cancel);
    }
}