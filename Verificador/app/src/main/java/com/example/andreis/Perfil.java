package com.example.andreis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Perfil extends Activity implements View.OnClickListener{
    public Button sig;
    public EditText name;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        /////////////////////////////////////////////////////

        sig=(Button) findViewById(R.id.sig1);
        name=(EditText) findViewById(R.id.nombre);
        sig.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String verify = name.getText().toString();
        if(verify.isEmpty()){
            name.setError("Nose ingreso caracter");
        }else
            Toast.makeText(this,"Listo",Toast.LENGTH_LONG).show();

    }
}
