package com.example.bd_app_tfc_alberto.clases;

public class Usuario {
    private String nombre;
    private String email;
    private int phone;

    public Usuario(String nombre, String email, int phone) {
        this.nombre = nombre;
        this.email = email;
        this.phone = phone;
    }
    public Usuario(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
