package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.db.DbDataSource;
import com.example.proyectofinal.db.DbOpenHelper;
import com.example.proyectofinal.models.Producto;
import com.example.proyectofinal.models.Usuario;

public class DetalleActivity extends AppCompatActivity {

    TextView tvNombre_Dest, tvTelefono, tvNombre_Prod, tvTipo_Prod, tvDireccion, tvEstado, tvPrecio, tvFecha;
    TextView tvNombre_Despachador;
    Button btn_preparacion, btn_camino, btn_entregado;

    DbDataSource dataSource;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle("DETALLE ENTREGA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new DbDataSource(this);

        Intent intent = getIntent();

        Producto producto = (Producto) getIntent().getSerializableExtra("producto");
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        String nombre = producto.getNombre();
        id = producto.getId();


        tvNombre_Dest = findViewById(R.id.tvNombre_Dest);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvNombre_Prod = findViewById(R.id.tvNombre_Prod);
        tvTipo_Prod = findViewById(R.id.tvTipo_Prod);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvEstado = findViewById(R.id.tvEstado);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvFecha = findViewById(R.id.tvFecha);
        //tvNombre_Despachador = findViewById(R.id.tvNombre_Despachador);


        btn_preparacion = findViewById(R.id.btn_preparacion);
        btn_camino = findViewById(R.id.btn_camino);
        btn_entregado = findViewById(R.id.btn_entregado);

        tvNombre_Dest.setText(producto.getNombre_dest());
        tvTelefono.setText(producto.getTelefono());
        tvNombre_Prod.setText(producto.getNombre());
        tvTipo_Prod.setText(producto.getTipo_prod());
        tvDireccion.setText(producto.getDireccion());
        tvEstado.setText(producto.getEstado());
        tvPrecio.setText(producto.getPrecio());
        tvFecha.setText(producto.getFecha_ent());
        //tvNombre_Despachador.setText(usuario.getNombre());

        Log.i("DetalleActivity", "Nombre recibido: " + nombre);

        btn_preparacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id = producto.getId();
                String estado = btn_preparacion.getText().toString();
                dataSource.actualizarEstado(id, estado);
                tvEstado.setText("En Preparación");
            }
        });

        btn_camino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id = producto.getId();
                String estado = btn_camino.getText().toString();
                dataSource.actualizarEstado(id, estado);
                tvEstado.setText("En Camino");
            }
        });

        btn_entregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id = producto.getId();
                String estado = btn_entregado.getText().toString();
                dataSource.actualizarEstado(id, estado);
                tvEstado.setText("Entregado");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_eliminar:
                //  Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                dataSource.openDB();
                eliminarProducto();
                dataSource.closeDB();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void eliminarProducto(){
        //id del contacto
        dataSource.eliminarProducto(id);
        setResult(1);
        finish();
    }


    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actualizar_estado, menu);
        return  true;
    }*/


    //@Override
    /*public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        SQLiteOpenHelper dnhelper = new DbOpenHelper(this);
        SQLiteDatabase db;
        DbOpenHelper helper;
        db = dnhelper.getWritableDatabase();
        int id = item.getItemId();*/

        /*
        switch (id){
            case android.R.id.home:
                finish();
                break;
            //Eliminar contacto
            case R.id.home:
                if(id == R.id.action_preparación){
                    estado = id;
                    int sql = db.update("productos", values, "estado="+ estado, null);
                    db.close();
                    Toast.makeText(this, "Cambiado con exito", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.action_camino){
                    int sql = db.update("productos", values, "estado="+ estado, null);
                    db.close();
                    Toast.makeText(this, "Cambiado con exito", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.action_entregado){
                    int sql = db.update("productos", values, "estado="+ estado, null);
                    db.close();
                    Toast.makeText(this, "Cambiado con exito", Toast.LENGTH_SHORT).show();
                }

            default:
                break;
        }
*/

      //  return super.onOptionsItemSelected(item);
    //}
}