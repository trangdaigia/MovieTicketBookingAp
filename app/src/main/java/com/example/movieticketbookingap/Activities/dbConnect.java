package com.example.movieticketbookingap.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class dbConnect extends SQLiteOpenHelper {

    public static final String dbName = "Login.db";


    public dbConnect(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table  users (ID INTEGER PRIMARY KEY AUTOINCREMENT,fullname TEXT, Email TEXT, Phone TEXT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
        onCreate(MyDB);
    }

    public Boolean insertData(String fullname, String Email, String Phone, String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("Email",Email);
        contentValues.put("Phone",Phone);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = MyDB.insert("users",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Boolean updatepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",password);
        long result = MyDB.update("users",contentValues,"username = ?",new String [] {username});
        if(result==-1) return false;
        else return true;
    }
    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?",new String[]{username});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password=?",new String []{username,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
