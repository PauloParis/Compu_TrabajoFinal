package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.db.DbDataSource;
import com.example.proyectofinal.models.Producto;

public class AgregarProductoActivity extends AppCompatActivity {

    EditText etNombre_Dest, etTelefono, etNombre_Prod, etTipo, etDireccion, etEstado, etPrecio, etFecha;
    DbDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setTitle("Agregar");

        dataSource = new DbDataSource(this);

        etNombre_Dest = findViewById(R.id.etNombre_Dest);
        etTelefono = findViewById(R.id.etTelefono);
        etNombre_Prod = findViewById(R.id.etNombre_Prod);
        etTipo = findViewById(R.id.etTipo);
        etDireccion = findViewById(R.id.etDireccion);
        etEstado = findViewById(R.id.etEstado);
        etPrecio = findViewById(R.id.etPrecio);
        etFecha = findViewById(R.id.etFecha);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agregar_producto_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_guardar_producto:
                dataSource.openDB();
                guardarproducto();
                dataSource.closeDB();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void guardarproducto(){

        String nombre_dest = etNombre_Dest.getText().toString();
        String telefono = etTelefono.getText().toString();
        String nombre_prod = etNombre_Prod.getText().toString();
        String tipo = etTipo.getText().toString();
        String direccion = etDireccion.getText().toString();
        String estado = etEstado.getText().toString();
        String precio = etPrecio.getText().toString();
        String fecha = etFecha.getText().toString();

        if(crearContacto(nombre_dest,telefono,nombre_prod,tipo,direccion,estado,precio,fecha) != -1){
            Toast.makeText(this, "Producto Agregado", Toast.LENGTH_SHORT).show();
            setResult(1);
            finish();
        }
        else{
            Log.i("AregarProductoActivity", "ERROR");
        }

    }

    public long crearContacto (String nombre_dest, String telefono, String nombre_prod, String tipo, String direccion, String estado, String precio, String fecha){

        Producto producto = new Producto();
        producto.setNombre_dest(nombre_dest);
        producto.setTelefono(telefono);
        producto.setNombre(nombre_prod);
        producto.setTipo_prod(tipo);
        producto.setDireccion(direccion);
        producto.setEstado(estado);
        producto.setPrecio(precio);
        producto.setFecha_ent(fecha);

        producto = dataSource.insertarProducto(producto);

        return producto.getId();
    }



}