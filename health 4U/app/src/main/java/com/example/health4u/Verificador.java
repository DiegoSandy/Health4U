package com.example.health4u;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Verificador {
    //para datos obligatorios
    public boolean maxCaracteres(String nombre, int max){
        boolean correcto=false;
        int longitud = nombre.length();
            if ((longitud > 0 && longitud <=max) && CaractdifEsp(nombre) ) {
                correcto = true;
            }

        return correcto;
    }
    public String ErrorDatosNoOpcionales(String nombre, int max){
        String error="";
        int longitud = nombre.length();
        if(nombre.isEmpty() || !CaractdifEsp(nombre)){
            error="No se ingreso texto";}
        else {
            if (longitud > max) {
                error = "texto maximo excedido";
            }
        }
        return error;
    }
    //mensaje de error de los datos opcionales
    public String ErrorDatosOpcionales(String nombre, int max){
        String menError="";
        if(!datosOpcionales(nombre, max) ){
            menError="texto maximo excedido";
        }
        return menError;
    }
    //para datos opcionales
    public boolean datosOpcionales(String cadena, int max){
        boolean bandera=false;
        int longitud = cadena.length();
        if(cadena.isEmpty()){
            bandera = true;
        }else
        if(longitud > 0 && longitud <=max){
            bandera = true;
        }else {
            bandera = false;
        }
        return bandera;
    }


    //Verifica ue el texto sea diferente de espacio
    public boolean CaractdifEsp(String nombre){
        boolean res=false;
        boolean isChar=true;
        int contador=0;
        int longitud = nombre.length();
        for (int i = 0; i <longitud ; i++) {
            char ver= nombre.charAt(i);
            if(ver==' '){
                contador++;
            }
        }
        if (contador!=longitud) {
            res = true;
        }else{
            res = false;
        }
        return res;
    }
    //verifica dia mes año
    public boolean verificadorFechaValida(String a){
        Date actual = new Date();
        int anioActual = actual.getYear()+1900;
        String dia = a.substring(0,2);
        String mes = a.substring(3,5);
        String anio = a.substring(6,10);
        boolean res= false;
        int number1 = Integer.parseInt(dia);
        int number2 = Integer.parseInt(mes);
        int number3 = Integer.parseInt(anio);

        if (number3 > 1922 && number3 < anioActual + 2) {
            if (number2 > 00 && number2 < 13) {
                if (number2 == 01 || number2 == 03 || number2 == 05 || number2 == 07 || number2 == 8 || number2 == 10 || number2 == 12) {
                    if (number1 > 0 && number1 < 32) {
                        res = true;
                    } else
                        res = false;
                } else {
                    if (number2 == 04 || number2 == 06 || number2 == 9 || number2 == 11) {
                        if (number1 > 0 && number1 < 31) {
                            res = true;
                        } else
                            res = false;
                    } else {
                        if (number2 == 02) { //es bisiesto
                            if ((number3 % 4) == 0) {
                                if (number1 > 0 && number1 < 30) {
                                    res = true;
                                } else
                                    res = false;
                            } else {     //no es bisiesto
                                if (number1 > 0 && number1 < 29) {
                                    res = true;
                                } else
                                    res = false;
                            }
                        }
                    }
                }
            } else
                res = false;
        } else
            res = false;


        return res;
    }
    //////error verificar dia valido fecha y anio
    public String DefinidorErrorFecha(String fecha){
        Date actual = new Date();
        int anioActual = actual.getYear()+1900;
        String dia = fecha.substring(0,2);
        String mes = fecha.substring(3,5);
        String anio = fecha.substring(6,10);
        String error= "";
        int number1 = Integer.parseInt(dia);//hora 00-23
        int number2 = Integer.parseInt(mes);//minuto 0-60
        int number3 = Integer.parseInt(anio);//hora 00-23
        if(number3>1922 && number3<anioActual+2){
            if(number2>00 && number2<13){
                if(number2==01 || number2==03 ||number2==05 ||number2==07 ||number2==8 ||number2==10 ||number2==12){
                    if(number1>0 && number1<32){
                    } else
                        error = "dia invalido";
                }else{
                    if(number2 == 04 ||number2 == 06 ||number2 == 9 ||number2 ==11){
                        if(number1>0 && number1<31){

                        } else
                            error = "dia invalido";
                    }else{
                        if(number2==02){ //es bisiesto
                            if((number3%4)==0){
                                if(number1>0 && number1<30){

                                }else
                                    error="dia invalido";
                            }else{     //no es bisiesto
                                if(number1>0 && number1<29){

                                }else
                                    error="dia invalido";
                            }
                        }
                    }
                }
            }else
                error="mes invalido";
        }else
            error="Año invalido";
        return error;
    }
    public boolean fecPermitida(String fech){
        boolean bandera = false;
        Date actual = new Date();
        int anioActual = actual.getYear()+1900;
        String anio = fech.substring(6,10);
        int number3 = Integer.parseInt(anio);
        int res = anioActual-number3;
        if(res<=87 && res>=17 ){
            bandera=true;
        }else{
            bandera=false;
        }
        return bandera;
    }
    public String ErrorFechPermitida(String fecha){
        String res ="";
        if(!fecPermitida(fecha)){
            res = "Año Invalido";
        }
        return res;
    }
    //verifica formato dd/MM/aaaa
    public  boolean verFormato(String str){
        boolean isNumeric = true;
        if(str.length()==10){
            String bar = "/";
            String barra1 = str.substring(2,3);
            String barra2 = str.substring(5,6);
            if(barra1.equals(bar) && barra2.equals(bar)){
                for (int i = 0; i < 2 && isNumeric==true; i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        isNumeric = false;
                    }
                }
                for (int i = 3; i < 5 && isNumeric==true; i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        isNumeric = false;
                    }
                }
                for (int i = 6; i < 10 && isNumeric==true; i++) {
                    if (!Character.isDigit(str.charAt(i))) {
                        isNumeric = false;
                    }
                }
            }else {
                isNumeric = false;
            }
        }else {
            isNumeric = false;

        }
        return isNumeric;
    }
    //////////Mensaje ErrorFormatofecha
    public String definidorErrorFormatoFecha(String fecha){
        String error="";
        if(!verFormato(fecha)){
            error="Formato invalido";
        }
        return error;
    }
    //verificador del peso
    public boolean verificarPeso(String peso){
        boolean correcto=true;
        if(!peso.isEmpty()){
            String sub="-";

            if(peso.contains(sub)){
                correcto=false;
            }else{
                double pesoEn= Double.parseDouble(peso);
                if(pesoEn<45 || pesoEn>180 ){
                    correcto=false;
                }
            }
        }else{
            correcto=true;
        }
        return correcto;
    }
    //Si el metodo de arriba les retorna falso entonces deben llamar al siguiente metodo para que les retorne el tipo de error
    //suponiendo que estan manejando un edit text de tipo NUMERO
    public String definidorDelErrorPeso(String peso){
        String menError="";
        String sub="-";
        if(!peso.isEmpty()){
            if(peso.contains(sub)){
                menError="Numero negativo invalido ";
            }else{
                double pesoEn= Double.parseDouble(peso);
                if(pesoEn<45 || pesoEn>180 ){
                    menError="Error en el peso";
                }
            }
        }
        return menError;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean verificadorFechaCita(String fecha ){
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
    public String errorFechaCita(String fecha){
        String error = "";
        if(!verificadorFechaCita(fecha)) {
            error = "fecha antigua";
        }
        return error;
    }

    //////////////////////// dosis a consumir sea mayor a 0 y menor a 2000


    public boolean verificardosis(String dosis){
        boolean correcto=true;
        if(!dosis.isEmpty()){
            String sub="-";

            if(dosis.contains(sub)){
                correcto=false;
            }else{
                double DosisEn= Double.parseDouble(dosis);
                if(DosisEn<0 || DosisEn>1000 ){
                    correcto=false;
                }
            }
        }else{
            correcto=true;
        }
        return correcto;
    }
    //Si el metodo de arriba les retorna falso entonces deben llamar al siguiente metodo para que les retorne el tipo de error
    //suponiendo que estan manejando un edit text de tipo NUMERO
    public String definidorDelErrordosis(String Dosis){
        String menError="";
        String sub="-";
        if(!Dosis.isEmpty()){
            if(Dosis.contains(sub)){
                menError="Numero negativo invalido";
            }else{
                double DosisEn= Double.parseDouble(Dosis);
                if(DosisEn<0 || DosisEn>1000 ){
                    menError="Error en la Dosis";
                }
            }
        }
        return menError;
    }
    public boolean periodo(String per){
        boolean bandera=false;
        int number = Integer.parseInt(per);
        if(per.length() >0 && per.length()<3){
            if(number>0 && number<30){
                bandera =true;
            }else{
                bandera = false;
            }
        }else{
            bandera = false;
        }
        return bandera;
    }
    public String ErrorPeriodo(String per){
        String res="";
        if(!periodo(per)){
            res = "Numero Invalido";
        }
        return res;
    }
   /* public boolean verificardosis(String dosis) {
        boolean correcto = true;
        String sub = "-";
        int a = dosis.length();
        String respuesta="";

        if(!dosis.isEmpty()) {
            String cadena = dosis.substring(a-2,a);//parte string
            String entero =dosis.substring(0,a-2);//parte numerica
            if (entero.contains(sub)) {
                correcto = false;
            } else {
                double dos = Double.parseDouble(entero);
                if (dos < 0 || dos > 2000) {
                    correcto = false;
                }
            }
        }else{
            correcto = true;
        }
        return correcto;
    }*/

    //Si el metodo de arriba les retorna falso entonces deben llamar al siguiente metodo para que les retorne el tipo de error
    //suponiendo que estan manejando un edit text de tipo NUMERO
   /* public String definidorDelErrordosis(String dosis) {
        String menError = "";
        String sub = "-";

        int a=dosis.length();
        String cadena = dosis.substring(a-2,a);//parte string
        String entero =dosis.substring(0,a-2);//parte numerica
        if (entero.contains(sub)) {
            menError = "Numero negativo invalido ";
        } else {
            double dos = Double.parseDouble(entero);
            if (dos < 0 || dos > 2000) {
                menError = "Error en la dosis";
            }
        }

        return menError;
    }*/



    //////////
    ///////// error fechafininicio (fecha fin --- fecha menor a inicio)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean verificadorFechaIniFin(String fechaIni, String fechaFin){
        boolean correcto=false;
        //Verificamos si la fecha es valida
        if(verificadorFechaValida(fechaIni) && verificadorFechaValida(fechaFin) ){
            //Parseamos el string en un objeto tipo fecha
            LocalDate fechaInicio= LocalDate.parse(fechaIni,DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate fechaFinn= LocalDate.parse(fechaFin,DateTimeFormatter.ofPattern("d/M/yyyy"));
            int diferencia= (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinn);
            //Verifica la diferencia en dias, si es mayor o igual a 1 dia
            if(diferencia > 0) {
                correcto=true;
            }
        }
        return correcto;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String ErrorVerificadorFechaIniFin(String fechaIni, String fechaFin){
        String error="";
        //Verificamos si la fecha es valida
        if(verificadorFechaValida(fechaIni) && verificadorFechaValida(fechaFin) ){
            //Parseamos el string en un objeto tipo fecha
            LocalDate fechaInicio= LocalDate.parse(fechaIni,DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate fechaFinn= LocalDate.parse(fechaFin,DateTimeFormatter.ofPattern("d/M/yyyy"));
            int diferencia= (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinn);
            //Verifica la diferencia en dias, si es mayor o igual a 1 dia
            if(diferencia < 0) {
                error="fecha menor a fecha inicio";
            }if(diferencia == 0){
                error = "fecha igual a Fecha inicio";
            }
        }
        return error;
    }
    //si la fecha es igual a la actual verificar hora

/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean verificarHoraValidaCita(String hora, String fecha){
        boolean correcto=true;

        LocalDate fechaCita= LocalDate.parse(fecha,DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalDate date = LocalDate.now();
        int diferenciaDias= (int) ChronoUnit.DAYS.between(date, fechaCita);
        if(diferenciaDias==0 ){
            LocalTime time= LocalTime.now();
            int minutosHoraActual= time.getMinute();
            int horasHoraActual= time.getHour();
            int pos = hora.indexOf(':');
            int mitadIzq= Integer.parseInt(hora.substring(0, pos)); //hora
            int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()));//minutos

            int horas = mitadIzq - horasHoraActual;
            int minutos = mitadDer - minutosHoraActual;
            if(horas<0){
                correcto=false;
            }else{
                if(horas == 0){
                    if(minutos<=0){
                        correcto=false;
                    }else{
                        correcto=true;
                    }
                }else{
                    correcto=true;
                }
            }
        }

        return correcto;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean verificarHoraValidaCita(String hora, String fecha){
        boolean correcto=false;
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Date dateObj = calendar.getTime();
        String fechahoy = dtf.format(dateObj);
        if(fecha.equals(fechahoy) ){
            LocalTime time= LocalTime.now();
            int minutosHoraActual= time.getMinute();
            int horasHoraActual= time.getHour();
            int pos = hora.indexOf(':');
            int mitadIzq= Integer.parseInt(hora.substring(0, pos)); //hora
            int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()));//minutos
            int horas = mitadIzq - horasHoraActual;
            int minutos = mitadDer - minutosHoraActual;
            if(horas<0){
                correcto=false;
            }else{
                if(horas == 0){
                    if(minutos<=0){
                        correcto=false;
                    }else{
                        correcto=true;
                    }
                }else{
                    correcto=true;
                }
            }
        }
        return correcto;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String ErrorVerificadorHoraCita(String hora, String fecha){
        String correcto="";

        LocalDate fechaCita= LocalDate.parse(fecha,DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalDate date = LocalDate.now();
        int diferenciaDias= (int) ChronoUnit.DAYS.between(date, fechaCita);
        if(diferenciaDias==0 ){
            LocalTime time= LocalTime.now();
            int minutosHoraActual= time.getMinute();
            int horasHoraActual= time.getHour();
            int pos = hora.indexOf(':');
            int mitadIzq= Integer.parseInt(hora.substring(0, pos));
            int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()));

            int resta = mitadIzq - horasHoraActual;
            if((horasHoraActual==mitadIzq && minutosHoraActual<=0) || (resta<0)){
                correcto="Hora invalida";
            }

        }

        return correcto;

    }
    public boolean verificarHora(String a){
        boolean correcto=true;

        if(a.contains("H")){
            correcto=false;
        }
        return correcto;
    }

}

