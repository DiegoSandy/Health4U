package com.example.health4u;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;
import java.time.format.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Verficador {
        @RequiresApi(api = Build.VERSION_CODES.O)
        public static void main(String[] args) {
                System.out.println("Joda Tima");
                LocalDate date = LocalDate.now(); //07/04/2022
                System.out.println(date);
                LocalDate fecha = LocalDate.parse("6/04/2022",DateTimeFormatter.ofPattern("d/M/yyyy"));
                System.out.println(fecha);
                long edad =  ChronoUnit.DAYS.between(date, fecha);
                System.out.println("Tuedad es de "+ edad);
                String fech="6/11/2030";
                LocalDate fechaS = LocalDate.parse(fech,DateTimeFormatter.ofPattern("d/M/yyyy"));
                System.out.println(fechaS);
                boolean correcto = verificarNomPerfil("");
                System.out.println(correcto);
                String error = definidorDelErrorPeso("209");
                System.out.println(error);
                String hora="1003:04";
                int pos = hora.indexOf(':');
                String hora2= "00:54";


                int mitadIzq= Integer.parseInt(hora.substring(0, pos));
                int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()));
                System.out.println(mitadIzq+" "+mitadDer);
        }




        //Para verificar la cantidad de caracteres del nombre, la enefermedad de base
        public static boolean verificarNomPerfil(String nombre){
                boolean correcto=false;
                int longitud = nombre.length();
                if(!nombre.isEmpty()){
                      correcto=true;
                }
                if(longitud>0 && longitud< 21){
                        correcto=true;
                }
                return correcto;
        }
        //Metodo en caso de que sea false
        public static String definidorDelErrorNombre(String nombre){
                String error="";
                if(nombre.isEmpty()){
                        error="Nose ingreso caracter";}
               else {
                   error="El nombre debe tener menos de 20 letras";
               }
               return error;
        }
        public static boolean verificarEnfBase(String nombre){
                boolean correcto=false;
                int longitud = nombre.length();

                if(longitud>0 && longitud< 21){
                        correcto=true;
                }
                return correcto;
        }
        public static boolean verificarPeso(String peso){
                boolean correcto=true;
                String sub="-";

                if(peso.contains(sub)){
                        correcto=false;
                }
                else{
                        double pesoEn= Double.parseDouble(peso);
                        if(pesoEn>200){
                                correcto=false;
                        }
                }
          return correcto;
        }
        //Si el metodo de arriba les retorna falso entonces deben llamar al siguiente metodo para que les retorne el tipo de error
        //suponiendo que estan manejando un edit text de tipo NUMERO
        public static String definidorDelErrorPeso(String peso){
                String menError="";
                String sub="-";
                if(peso.contains(sub)){
                        menError="El peso ingresado contiene un caracter no válido, un guion '-' ";
                }
                else{
                        double pesoEn= Double.parseDouble(peso);
                        if(pesoEn>200){
                                menError="El peso debe ser menor a 200 kg";
                        }
                }
                return menError;
        }
        public static boolean verificarNomCita(String nombreCita){
                boolean correcto=false;
                int longitud = nombreCita.length();
                if(!nombreCita.isEmpty()){
                        correcto=true;
                }
                if(longitud>0 && longitud< 26){
                        correcto=true;
                }
                return correcto;
        }
        //Suponiendo que al llenar el texto solo se les deplace el teclado con numeros y un ":"
        public static boolean verificadorFormatoHora(String hora){
                boolean correcto= false;
                String sub=":";
                if(!hora.isEmpty()){
                    correcto=true;
                }
                if(hora.contains(sub)){
                        //Para sacar la posicion de los :
                        int pos = hora.indexOf(':');
                        int mitadIzq= Integer.parseInt(hora.substring(0, pos-1));
                        int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()-1));
                        if(mitadIzq>=0 && mitadIzq<=12){
                                if(mitadDer>=0 && mitadDer<60){
                                        correcto=true;
                                }

                        }

                }
                return correcto;

        }


       //Verifica si la fecha existe y si el formato es valido
        public static boolean verificadorFechaValida(String fecha){
               boolean correcto=false;

               return correcto;

       }
       @RequiresApi(api = Build.VERSION_CODES.O)
       public static boolean verificadorFechaCita(String fecha ){
               boolean correcto=false;
               if (verificadorFechaValida(fecha)) {
                       LocalDate date = LocalDate.now();
                       LocalDate fechaEnFormato = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy"));
                       int diferencia = (int) ChronoUnit.DAYS.between(date, fechaEnFormato);
                       //Verificamos si la fecha es
                       if (diferencia >= 0) {
                               correcto = true;
                       }
               }

               return correcto;
       }
       @RequiresApi(api = Build.VERSION_CODES.O)
        //Verifica si la fecha inicio es antes del fin
        public static boolean verificadorFechaIniFin(String fechaIni, String fechaFin){
                boolean correcto=false;
                //Verificamos si la fecha es valida
                if(verificadorFechaValida(fechaIni) && verificadorFechaValida(fechaFin) ){
                        //Parseamos el string en un objeto tipo fecha
                        LocalDate fechaInicio= LocalDate.parse(fechaIni,DateTimeFormatter.ofPattern("d/M/yyyy"));
                        LocalDate fechaFinn= LocalDate.parse(fechaFin,DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int diferencia= (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinn);
                      //Verifica la diferencia en dias, si es mayor o igual a 1 dia
                      if(diferencia >= 0) {
                              correcto=true;
                      }
                }
                return correcto;
       }

       public static boolean verificarHoraValidaCita(String hora){
                boolean correcto=false;
                if(verificadorFormatoHora(hora)){

                }

                return correcto;
       }





        public static boolean verificarDireccionDescripcion(String direccion){
                boolean correcto=false;
                int longitud = direccion.length();
                if(longitud>0 && longitud< 26){
                        correcto=true;
                }
                return correcto;
        }
}
