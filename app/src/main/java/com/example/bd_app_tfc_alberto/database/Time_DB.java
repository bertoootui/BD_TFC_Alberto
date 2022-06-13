package com.example.bd_app_tfc_alberto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bd_app_tfc_alberto.clases.Horas;

import java.util.ArrayList;

public class Time_DB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "time";
    private static final int DATABASE_VERSION = 1;
    private static final String ID = "id";
    private static final String TIME = "time";
    public Time_DB(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + " ("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TIME + " VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean getTime(String time)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT time FROM time WHERE time = ?",new String[]{time});
        if(c.moveToFirst())
        {
            return true;
        }
        else return false;
    }

    public ArrayList<Horas> getData()
    {
        ArrayList<Horas> listatime = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT time FROM time",new String[]{});
        if(c.moveToFirst())
        {
            do{
                listatime.add(new Horas(c.getString(0)));
            }while (c.moveToNext());
        }
        return listatime;
    }

    public void setData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,1,1);
        ContentValues contentValues = new ContentValues();
        int hora = 11;
        int min = 0;
        int contmin = 0;
        for(int i=0;i<20;i++)
        {
            if(contmin == 2) {hora++;min=0;contmin=0;}
            String horas = String.valueOf(hora);

            String mins = min+"0";
            if(contmin == 1)mins = String.valueOf(min);
            contentValues.put("time",horas+":"+mins);
            min = min+30;
            contmin ++;
            db.insert(TABLE_NAME,null,contentValues);
        }
    }
}
