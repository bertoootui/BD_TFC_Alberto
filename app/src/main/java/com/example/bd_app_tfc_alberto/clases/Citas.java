package com.example.bd_app_tfc_alberto.clases;

import java.util.ArrayList;

public class Citas {

    private int id_user;
    private String date;
    private String time;
    private ArrayList<String>listatime = new ArrayList<>();

    public Citas(int id_user, String date, String time) {
        this.id_user = id_user;
        this.date = date;
        this.time = time;
    }

    public Citas(){};

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public ArrayList<String> getListatime() {

        listatime.add("12:00");
        listatime.add("12:30");
        listatime.add("13:00");
        listatime.add("13:30");
        listatime.add("14:00");
        listatime.add("14:30");
        listatime.add("15:00");
        listatime.add("15:30");
        listatime.add("16:00");
        listatime.add("16:30");
        listatime.add("17:00");
        listatime.add("17:30");
        listatime.add("18:00");
        listatime.add("18:30");
        listatime.add("19:00");
        listatime.add("19:30");
        listatime.add("20:00");
        listatime.add("20:30");


        return listatime;
    }
}
