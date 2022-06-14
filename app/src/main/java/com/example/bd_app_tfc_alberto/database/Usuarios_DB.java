package com.example.bd_app_tfc_alberto.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuarios_DB extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "users";
    private static final int DATABASE_VERSION = 1;

    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String EMAIL = "email";
    private static final String TLFN = "tlfn";
    private static final String PASSWORD = "password";


    public Usuarios_DB(@Nullable Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME + " ("+
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NOMBRE + " VARCHAR,"+
                EMAIL + " VARCHAR,"+
                TLFN + " INTEGER,"+
                PASSWORD + " VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int saveDataRegister(String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",md5(password));
        db.insert(TABLE_NAME,null,contentValues);

        Cursor c = db.rawQuery("SELECT * FROM users ORDER BY id DESC LIMIT 1",new String[]{});
        c.moveToFirst();
        return c.getInt(0);
    }

    public void getData(int id_user)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE ID =?",new String[]{String.valueOf(id_user)});
        if(c.moveToFirst())
        {

        }
    }
    private static String md5(String s) { try {

        // Create MD5 Hash
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();

        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        for (int i=0; i<messageDigest.length; i++)
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
        return "";

    }

    public int getIdUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM users WHERE email = ?",new String[]{email});
        c.moveToFirst();
        Integer id = c.getInt(0);
        if(id == null) id =0;
        return c.getInt(0);
    }
}
