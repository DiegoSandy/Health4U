package com.example.andreis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public Button NewP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewP = (Button) findViewById(R.id.crear);
        NewP.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        if(id == NewP.getId()){
            Intent i = new Intent(this, Perfil.class);
            startActivity(i);
        }
    }
}