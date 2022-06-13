package com.example.bd_app_tfc_alberto.clases;

public class Servicios {

    private String nombre;
    private String precio;

    public Servicios(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    public Servicios() {}//CONSTRUCTOR VACÍO

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
