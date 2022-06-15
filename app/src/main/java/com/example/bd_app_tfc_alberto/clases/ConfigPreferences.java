package com.example.bd_app_tfc_alberto.clases;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigPreferences {
    public void setEmpSel(Context context,int id_emp)
    {
        SharedPreferences sharedpref = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("id_emp",id_emp);
        editor.commit();
    }
    public int getEmpSel(Context context)
    {
        SharedPreferences sharedpref = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        return sharedpref.getInt("id_emp",-1);
    }
    public void setServSel(Context context, int id_serv)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id_serv",id_serv);
        editor.commit();
    }
    public int getServSel(Context context)
    {
        SharedPreferences sharedpref = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        return sharedpref.getInt("id_serv",-1);
    }

    public int getHoraSel(Context context) {
        SharedPreferences sharedpref = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        return sharedpref.getInt("hora",-1);
    }

    public void setHoraSel(int hora,Context context) {
        SharedPreferences sharedpref = context.getSharedPreferences("CONFIG",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("hora",hora);
        editor.commit();
    }
}
