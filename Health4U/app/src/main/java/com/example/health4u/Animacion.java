package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class Animacion extends AppCompatActivity {

    Timer time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion);

        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
<<<<<<< HEAD
                Intent next=new Intent(Animacion.this, Menu.class);
                startActivity(next);
                finish();
            }
        }, 2500);

=======
                Intent next=new Intent(Animacion.this, Medicamento1.class);
                startActivity(next);
            }
        }, 2500);
>>>>>>> 98a5e2fe45e4dd30b1d3e87698773f3b879bc014
    }



}