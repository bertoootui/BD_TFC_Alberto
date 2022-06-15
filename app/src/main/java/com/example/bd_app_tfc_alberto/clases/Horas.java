package com.example.bd_app_tfc_alberto.clases;

public class Horas {
    private String time;
    private boolean isSelect;
    private boolean select;

    public Horas(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
