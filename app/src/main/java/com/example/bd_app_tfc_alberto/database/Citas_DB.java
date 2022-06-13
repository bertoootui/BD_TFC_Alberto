package com.example.bd_app_tfc_alberto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Citas_DB extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "citas";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    private static final String ID_USER = "id_user";
    private static final String DATE = "date";
    private static final String TIME = "time";
    private static final String ID_EMP = "id_emp";
    private static final String ID_SERV = "id_serv";

    public Citas_DB(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                ID_USER + " INTEGER,"+
                DATE + " VARCHAR,"+
                ID_EMP + " INTEGER,"+
                ID_SERV + " INTEGER,"+
                TIME +" VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public void addDate(String date,String time, int id_emp, int id_serv,int id_user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("time",time);
        contentValues.put("id_user",id_user);
        contentValues.put("id_emp",id_emp);
        contentValues.put("id_serv",id_serv);
        db.insert(TABLE_NAME,null,contentValues);
    }


    public ArrayList<String> getTimes(String date,int id_emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> listatime = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT time FROM citas WHERE id_emp = ? AND date = ?",new String[]{String.valueOf(id_emp),date});
        if(c.moveToFirst())
        {
            do{
                String time = c.getString(0);
                listatime.add(time);
            }while (c.moveToNext());
        }
        return listatime;
    }
}
