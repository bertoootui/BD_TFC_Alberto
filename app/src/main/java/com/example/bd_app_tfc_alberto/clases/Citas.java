package com.example.bd_app_tfc_alberto.clases;

import java.util.ArrayList;

public class Citas {

    private int id;
    private String date;
    private String time;
    private String servicio;
    private String empleado;


    public Citas(int id,String date, String time, String servicio, String empleado) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.servicio = servicio;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
