package com.example.health4u;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RecordarCita2 extends AppCompatActivity {
    private Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton2 = (Button) findViewById(R.id.BotonAlerta);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){

                AlertDialog.Builder alerta = new AlertDialog.Builder(RecordarCita2.this);
                alerta.setMessage("Desea cancelar la alarma?")
                        .setCancelable(false);
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                })


                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialof, int which) {

                                dialog.cancel();

                            }
                        })
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Cancelar la alarma");
                titulo.show();
            }

        } )
    }
}




