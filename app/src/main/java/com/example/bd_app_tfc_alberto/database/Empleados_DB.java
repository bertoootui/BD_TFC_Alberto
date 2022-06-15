package com.example.bd_app_tfc_alberto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bd_app_tfc_alberto.clases.Empleados;

import java.util.ArrayList;

public class Empleados_DB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "empleados";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String FOTO = "foto";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";





    public Empleados_DB(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NOMBRE + " VARCHAR,"+
                EMAIL + " VARCHAR,"+
                PASSWORD + " VARCHAR,"+
                FOTO + " VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


    public String getNameEmp(int id_emp) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT nombre FROM empleados WHERE id = ?",new String[]{String.valueOf(id_emp)});
        if(c.moveToFirst())
        {
            return c.getString(0);
        }
        else return "";
    }

    public void setEmpleados()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,1,1);
        ContentValues contentValues = new ContentValues();


            contentValues.put("nombre","Berto");
            db.insert(TABLE_NAME,null,contentValues);

        contentValues.put("nombre","Jose");
        db.insert(TABLE_NAME,null,contentValues);
        contentValues.put("nombre","Ruben");
        db.insert(TABLE_NAME,null,contentValues);
        contentValues.put("nombre","Carlos");
        db.insert(TABLE_NAME,null,contentValues);
        contentValues.put("nombre","David");
        db.insert(TABLE_NAME,null,contentValues);

    }

    public ArrayList<Empleados> getEmpleados()
    {
        ArrayList<Empleados> listaemp = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM empleados",new String[]{});
        if(c.moveToFirst())
        {
            do{
                listaemp.add(new Empleados(c.getString(1)));
            }while (c.moveToNext());

        }
        return listaemp;
    }
}
