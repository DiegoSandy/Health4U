package com.example.health4u;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import static com.example.health4u.Inicio.administrador;

public class detalleNotificacion extends AppCompatActivity {

    private TextView tx1;
    private static String infor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_notificacion);
        tx1 = (TextView) findViewById(R.id.editTextTextPersonName2);
        //Bundle recibe = getIntent().getExtras();
        //infor = recibe.getString("keyDatos");
        //String horaAct=infor;//getIntent().getStringExtra("h");
        //Log.d("deta",infor);
        /*SQLiteDatabase bd=administrador.getWritableDatabase();
        Cursor cursorDb=bd.rawQuery("select nombreMedicamento, dosisMed, fechaInicioMed, fechaFinMed from medicamento where '"+horaAct+"' like horaInicio", null);
        String data="no entro";
        if(cursorDb.moveToFirst()) {
            do {
                //Cursor cursorbd=VerificarBD();
                String name= cursorDb.getString(0) + "";
                String hora = cursorDb.getString(1)+ "";
                String fecha = cursorDb.getString(2) + "";
                String descripcion = cursorDb.getString(3) + "";
                String direccion = cursorDb.getString(4) + "";

                data = "Nombre de la Cita" +name + "\nA las:" + hora + "\nEl:" + fecha + "\nDescripción:" + descripcion + "\nDirección:" + direccion ;

            }while (cursorDb.moveToNext());

        }
        bd.close();

         */
        tx1.setText(Medicamento3.data);
    }

    public static void setdata(String info){
        infor=info;
    }
}