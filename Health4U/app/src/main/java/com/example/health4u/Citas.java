package com.example.health4u;

public class Citas {
    private int id;
    private String nombreCita;
    private String fechaCita;
    private String horaCita;
    private String descripcionCita;
    private String direccionCita;

    public Citas(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCita() {
        return nombreCita;
    }

    public void setNombreCita(String nombre) {
        this.nombreCita = nombre;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String hora) {
        this.horaCita = hora;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fecha) {
        this.fechaCita = fecha;
    }

    public String getDescripcionCita() {
        return descripcionCita;
    }

    public void setDescripcionCita(String descripcion) {
        this.descripcionCita = descripcion;
    }

    public String getDireccionCita() {
        return direccionCita;
    }

    public void setDireccionCita(String direccion) {
        this.direccionCita = direccion;
    }

}


