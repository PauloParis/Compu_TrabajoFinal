package com.example.proyectofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "proyectofinal.db";
    public static int VERSION = 1;

    String CREATE_TABLE_USUARIO = "CREATE TABLE usuario(id INTEGER PRIMARY KEY autoincrement, nombre TEXT, correo TEXT, password TEXT);";
    String CREATE_TABLE_PRODUCTO = "CREATE TABLE producto(id INTEGER PRIMARY KEY autoincrement, nombre_dest TEXT, telefono TEXT, nombre_prod TEXT, tipo_prod TEXT, direccion TEXT, estado TEXT, precio  TEXT, fecha TEXT);";


    //String INSERT_USUARIO1 = "INSERT INTO usuario(nombre, correo, password) VALUES('Paulo', 'paulo.gutierrezp@utem.cl', '1234');";
    //String INSERT_PRODUCTO1 = "INSERT INTO producto(nombre_dest, telefono, nombre_prod, tipo_prod, direccion, estado, precio, fecha) " +
      //      "VALUES('Juan','123456789','Polera','Ropa','calle #1','Entregado','$5.000','20/12/2021') ";

    public DbOpenHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        Log.i("Openhelper", "Se creó tabla usuario");

        db.execSQL(CREATE_TABLE_PRODUCTO);
        Log.i("Openhelper", "Se creo tabla producto");


        //db.execSQL(INSERT_USUARIO1);
        //Log.i("Openhelper", "Se insertó usuario");
        //db.execSQL(INSERT_PRODUCTO1);
        //Log.i("Openhelper", "Se inserto producto");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    /*public Boolean checkCorreo(String correo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuario where correo=?", new String[] {correo});
        if(cursor.getCount()>0){ //ver si existe o no
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkPassword (String correo, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE correo=? and password=?", new String[] {correo, password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }*/

    //
    /*public Cursor colsultarUsuPas(String correo, String password)throws SQLException {
        Cursor cursor = null;
        cursor=this.getReadableDatabase().query("usuario", new String[]{"id", "nombre", "correo", "password"}, "correo like '"+correo+"'" + "and password like '"+password+"'", null,null, null, null);
        return cursor;
    }*/




}
