package com.example.andreis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Verificador {
    public boolean maxCaracteres(String nombre, int max){
        boolean correcto=false;
        int longitud = nombre.length();

        if(longitud>0 && longitud< max){
            correcto=true;
        }
        return correcto;
    }
    public String ErrorDatosOpcionales(String nombre, int max){
        String menError="";
        if(!maxCaracteres(nombre, max)){
            menError="texto excedido ";
        }
        return menError;
    }
    public String ErrorDatosNoOpcionales(String nombre, int max){
        String error="";
        int longitud = nombre.length();
        if(nombre.isEmpty()){
            error="No se ingreso texto";}
        else {
            if (longitud > max) {
                error = "texto excedido";
            }
        }
        return error;
    }
    public static boolean verificarPeso(String peso){
        boolean correcto=true;
        String sub="-";

        if(peso.contains(sub)){
            correcto=false;
        }
        else{
            double pesoEn= Double.parseDouble(peso);
            if(pesoEn>180){
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
    //Suponiendo que al llenar el texto solo se les deplace el teclado con numeros y un ":"

    public static boolean verificadorHora(String hora){
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
    /* caso1formato incorrecto
     * caso2 hora no valida
     * caso3 minuto no valido
     */

    public static String DefinidorDelErrorHora(String hora){
        String error="";
        String sub=":";
        if(hora.contains(sub)){
            //Para sacar la posicion de los :
            int pos = hora.indexOf(':');
            int mitadIzq= Integer.parseInt(hora.substring(0, pos));
            int mitadDer= Integer.parseInt(hora.substring(pos+1, hora.length()));
            if(mitadIzq>=0 && mitadIzq<=12){
                if(mitadDer>=0 && mitadDer<60){

                }else
                    error="minuto invalido";
            }else
                error="Hora no valida";

        }else
            error="Formato Incorrecto";
        return error;
    }
    public boolean vereficadorFechaValida(String a){
        Date actual = new Date();
        int anioActual = actual.getYear()+1900;
        String dia = a.substring(0,2);
        String mes = a.substring(3,5);
        String anio = a.substring(6,10);
        boolean res= false;
        int number1 = Integer.parseInt(dia);//hora 00-23
        int number2 = Integer.parseInt(mes);//minuto 0-60
        int number3 = Integer.parseInt(anio);//hora 00-23
        if(number3>1922 && number3<anioActual){
            if(number2>00 && number2<13){
                if(number2==01 || number2==03 ||number2==05 ||number2==07 ||number2==8 ||number2==10 ||number2==12){
                    if(number1>0 && number1<32){
                        res =true;
                    } else
                        res=false;
                }else{
                    if(number2 == 04 ||number2 == 06 ||number2 == 9 ||number2 ==11){
                        if(number1>0 && number1<31){
                            res =true;
                        } else
                            res=false;
                    }else{
                        if(number2==02){ //es bisiesto
                            if((number3%4)==0){
                                if(number1>0 && number1<30){
                                    res =true;
                                }else
                                    res = false;
                            }else{     //no es bisiesto
                                if(number1>0 && number1<29){
                                    res = true;
                                }else
                                    res = false;
                            }
                        }
                    }
                }
            }else
                res= false;
        }else
            res=false;

        return res;
    }
    //////vaerificar dia valido fecha y anio
    public static String DefinidorErrorFecha(String fecha){
        Date actual = new Date();
        int anioActual = actual.getYear()+1900;
        String dia = fecha.substring(0,2);
        String mes = fecha.substring(3,5);
        String anio = fecha.substring(6,10);
        String error= "";
        int number1 = Integer.parseInt(dia);//hora 00-23
        int number2 = Integer.parseInt(mes);//minuto 0-60
        int number3 = Integer.parseInt(anio);//hora 00-23
        if(number3>1922 && number3<anioActual){
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
    public static boolean verFormato(String fecha){
        boolean res=false;
        String barra1 = fecha.substring(2,3);
        String barra2 = fecha.substring(5,6);
        String bar = "/";
        int b = fecha.length();
        if(fecha.length()==10){
            if(barra1.equals(bar) && barra2.equals(bar)){
                res = true;
            }else
                res = false;
        }else
            res=false;
        return res;
    }
    //////////
    public static String definidorErrorFormatoFecha(String fecha){
        String error="";
        if(!verFormato(fecha)){
            error="Formato invalido";
        }
        return error;
    }
    public static boolean verificadorFechaIniFin(String fechaIni, String fechaFin){
        boolean correcto=false;
        //Verificamos si la fecha es valida
        if(vereficadorFechaValida(fechaIni) && vereficadorFechaValida(fechaFin) ){
            //Parseamos el string en un objeto tipo fecha
            LocalDate fechaInicio= LocalDate.parse(fechaIni, DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate fechaFinn= LocalDate.parse(fechaFin,DateTimeFormatter.ofPattern("d/M/yyyy"));
            int diferencia= (int) ChronoUnit.DAYS.between(fechaInicio, fechaFinn);
            //Verifica la diferencia en dias, si es mayor o igual a 1 dia
            if(diferencia >= 0) {
                correcto=true;
            }
        }
        return correcto;
    }
    public static String DefinidorErrorFechaIniFin(String fechaIni, String fechaFin){
        String error ="";
        if(!verificadorFechaIniFin(fechaIni,fechaFin)){
            error="revise la fecha de inicio y fin";
        }
    }
    //Verifica si la fecha existe y si el formato es valido
    public static boolean verificadorFechaCita(String fecha ){
        boolean correcto=false;
        if (vereficadorFechaValida(fecha)) {
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
}
