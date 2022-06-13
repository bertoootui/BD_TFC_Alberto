package com.example.bd_app_tfc_alberto.clases;

import com.example.bd_app_tfc_alberto.R;

import java.util.ArrayList;

public class Empleados {
    private String nombre;
    private String foto;
    private String email;
    private String password;
    private String tlfn;

    public Empleados(String nombre, String foto) {
        this.nombre = nombre;
        this.foto = foto;
    }
    public Empleados(String nombre) {
        this.nombre = nombre;
    }

    public Empleados() {}//CONSTRUCTOR VACÍO

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public ArrayList<Empleados> getEmpleados()
    {
        ArrayList<Empleados> listaemp = new ArrayList<>();
        Empleados emp1 = new Empleados("berto");
        listaemp.add(emp1);
        Empleados emp2 = new Empleados("berto1");
        listaemp.add(emp2);
        Empleados emp3 = new Empleados("berto2");
        listaemp.add(emp3);
        Empleados emp4 = new Empleados("berto3");
        listaemp.add(emp4);
        Empleados emp5 = new Empleados("berto4");
        listaemp.add(emp5);
        return listaemp;

    }
}
