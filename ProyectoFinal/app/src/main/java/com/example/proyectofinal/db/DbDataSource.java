package com.example.proyectofinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.proyectofinal.models.Producto;
import com.example.proyectofinal.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DbDataSource {

    SQLiteOpenHelper dnhelper;
    SQLiteDatabase db;

    public DbDataSource(Context context) {
        dnhelper = new DbOpenHelper(context);
    }

    public void openDB() {
        db = dnhelper.getWritableDatabase();
        Log.i("db", "openDB");
    }

    public void closeDB() {
        dnhelper.close();
        Log.i("db", "closeDB");
    }


    //Metodo que perimite insertar datos en la tabla usuario
    /*public Usuario insertarUsuario(Usuario usuario) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", usuario.getNombre());
        valores.put("correo", usuario.getCorreo());
        valores.put("password", usuario.getPassword());
        long insertid = db.insert("usuario", null, valores);
        usuario.setId(insertid);
        return usuario;

    }*/

    public Producto insertarProducto(Producto producto) {
        ContentValues valores = new ContentValues();
        valores.put("nombre_dest", producto.getNombre_dest());
        valores.put("telefono", producto.getTelefono());
        valores.put("nombre_prod", producto.getNombre());
        valores.put("tipo_prod", producto.getTipo_prod());
        valores.put("direccion", producto.getDireccion());
        valores.put("estado", producto.getEstado());
        valores.put("precio", producto.getPrecio());
        valores.put("fecha", producto.getFecha_ent());
        long insertid = db.insert("producto", null, valores);
        producto.setId(insertid);
        return producto;
    }




    public List<Producto> obtenerProductos(){

        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM producto";
        Cursor cursor = db.rawQuery(query, null);

        Log.i("db", "Filas retornadas: "+cursor.getCount());

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Producto producto = new Producto();
                producto.setId(cursor.getLong(0));
                producto.setNombre_dest(cursor.getString(1));
                producto.setTelefono(cursor.getString(2));
                producto.setNombre(cursor.getString(3));
                producto.setTipo_prod(cursor.getString(4));
                producto.setDireccion(cursor.getString(5));
                producto.setEstado(cursor.getString(6));
                producto.setPrecio(cursor.getString(7));
                producto.setFecha_ent(cursor.getString(8));

                productos.add(producto);
            }
        }

        return productos;
    }


    public Producto insertarProductos(Producto producto){
        ContentValues valores = new ContentValues();
        valores.put("nombre_dest", producto.getNombre_dest());
        valores.put("telefono", producto.getTelefono());
        valores.put("nombre_prod", producto.getNombre());
        valores.put("tipo_prod", producto.getTipo_prod());
        valores.put("direccion", producto.getDireccion());
        valores.put("estado", producto.getEstado());
        valores.put("precio", producto.getPrecio());
        valores.put("fecha", producto.getFecha_ent());
        long insertid = db.insert("producto",null,valores);
        producto.setId(insertid);

        return producto;
    }

    public boolean actualizarEstado(long id, String estado){
        boolean correcto = false;

        //dnhelper = new DbOpenHelper(this);
        db = dnhelper.getWritableDatabase();

        try{
            db.execSQL("UPDATE producto SET estado='" + estado + "'WHERE id='"+id+"' ;");
            correcto = true;
        }
        catch (Exception e){
            e.toString();
            correcto = false;
        }
        finally {
        }
        return correcto;
    }


    public boolean eliminarProducto(long id ){
        String where = "id=" +id;
        int result = db.delete("producto", where, null);

        return (result == 1);

    }


    /*public List<Producto> buscarProductos(String buscar){

        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM producto WHERE fecha LIKE'%"+buscar+"%' OR estado LIKE '%"+buscar+"%'";
        Cursor cursor = db.rawQuery(query, null);

        Log.i("db", "Filas retornadas: "+cursor.getCount());

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Producto producto = new Producto();
                producto.setId(cursor.getLong(0));
                producto.setNombre_dest(cursor.getString(1));
                producto.setTelefono(cursor.getString(2));
                producto.setNombre(cursor.getString(3));
                producto.setTipo_prod(cursor.getString(4));
                producto.setDireccion(cursor.getString(5));
                producto.setEstado(cursor.getString(6));
                producto.setPrecio(cursor.getString(7));
                producto.setFecha_ent(cursor.getString(8));

                productos.add(producto);
            }
        }

        return productos;
    }*/


    /*public boolean actualizarEstado(long id, Producto producto ){
        String where = "id=" +id;
        int result = db.delete("contacto", where, null);

        ContentValues values = new ContentValues();
        values.put("estado", producto.getEstado());

        int kjas = db.update("productos", producto, "estado= " + );
        return (result == 1);

    }*/


    /*
    public Cursor colsultarUsuPas(String correo, String password) throws SQLException {
        Cursor cursor = null;
        cursor = dnhelper.getReadableDatabase().query("usuario", new String[]{"id", "nombre", "correo", "password"}, "correo like '" + correo + "'" + "and password like '" + password + "'", null, null, null, null);
        return cursor;
    }*/


}




    //public List<Contacto> obtenerContactos(){
/*
        List<Contacto> contactos = new ArrayList<>();

        String query = "SELECT * FROM contacto";
        Cursor cursor = db.rawQuery(query, null);

        Log.i(TAG, "Filas retornadas: "+cursor.getCount());

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Contacto contacto = new Contacto();
                contacto.setId(cursor.getLong(0));
                contacto.setNombre(cursor.getString(1));
                contacto.setPaterno(cursor.getString(2));
                contacto.setMaterno(cursor.getString(3));
                contacto.setTelefono(cursor.getString(4));

                contactos.add(contacto);
            }
        }

        return contactos;
    }*/

    /*public Cursor colsultarUsuPas(String correo, String password) throws SQLException {
        Cursor cursor = null;

        List<Usuario> usuarios = new ArrayList<>();

        //String query = "SELECT * FROM usuario";
        String query =  "SELECT * FROM usuario WHERE correo like '"+correo+"'" + "and password like '"+password+"'";
        cursor = db.rawQuery(query, null);

        Log.i("db", "Filas retornadas: "+cursor.getCount());

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                //if(cursor.getString(2).equals(correo) && cursor.getString(3).equals(password)) {
                    Usuario usuario = new Usuario();
                    usuario.setId(cursor.getLong(0));
                    usuario.setNombre(cursor.getString(1));
                    usuario.setCorreo(cursor.getString(2));
                    usuario.setPassword(cursor.getString(3));


                    usuarios.add(usuario);
                //}

            }
        }


        //cursor=dnhelper.getReadableDatabase().query("usuario", new String[]{"id", "nombre", "correo", "password"}, "correo like '"+correo+"'" + "and password like '"+password+"'", null, null, null, null);
        return cursor;

    }*/




    //metodo que perimite validad si el usario existe
    /*public Cursor consultarUsuPas(Usuario usuario) throws SQLException {
        Cursor cursor = null;

        cursor = this.
        return cursor;
    }*/


    /*public List<Usuario> obtenerUsuarios(){

        List<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT * FROM usuario";
        Cursor cursor = db.rawQuery(query, null);

        Log.i("db", "Filas retornadas: "+cursor.getCount());

        if(cursor.getCount() > 0){
            while(cursor.moveToNext() && ){
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getLong(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setCorreo(cursor.getString(2));
                usuario.setPassword(cursor.getString(3));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }*/


    /*public boolean consultarUsuPas(String correo, String password ){
        String where_correo = "correo=" + correo;
        String where_password = "password" + password;
        int result = db("contacto", where, null);
        int

        return (result == 1);

    }*/




