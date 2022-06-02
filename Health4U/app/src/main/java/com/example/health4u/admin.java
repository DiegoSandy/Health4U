/*package com.example.health4u;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class admin extends SQLiteOpenHelper {
    public admin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        String Cita="create table CITA\n" +
                "(\n" +
                "   IDCITA               int not null AUTO_INCREMENT,\n" +
                "   IDPERFIL             int,\n" +
                "   NOMBRE_CITA          char(45) not null,\n" +
                "   primary key (IDCITA)\n" +
                ")";
        String Medicamento="create table MEDICAMENTO\n" +
                "(\n" +
                "   IDMEDICAMENTO        int not null AUTO_INCREMENT,\n" +
                "   IDPERFIL             int,\n" +
                "   NOMBRE_MED           char(45) not null,\n" +
                "   primary key (IDMEDICAMENTO)\n" +
                ")";
        String Perfil="create table PERFIL\n" +
                "(\n" +
                "   IDPERFIL             int not null AUTO_INCREMENT,\n" +
                "   NOMBRE               char(45) not null,\n" +
                "   primary key (IDPERFIL)\n" +
                ")";
        String alter1="alter table CITA add constraint FK_PROGRAMA foreign key (IDPERFIL)\n" +
                " references PERFIL (IDPERFIL) on delete restrict on update restrict";
        String alter2="alter table MEDICAMENTO add constraint FK_CONSUME foreign key (IDPERFIL)\n" +
                " references PERFIL (IDPERFIL) on delete restrict on update restrict";
        BaseDeDatos.execSQL(Cita);
        BaseDeDatos.execSQL(Medicamento);
        BaseDeDatos.execSQL(Perfil);
        BaseDeDatos.execSQL(alter1);
        BaseDeDatos.execSQL(alter2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}*/
