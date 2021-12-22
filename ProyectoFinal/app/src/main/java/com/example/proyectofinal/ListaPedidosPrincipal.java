package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectofinal.adapters.ProductoAdapter;
import com.example.proyectofinal.db.DbDataSource;
import com.example.proyectofinal.models.Producto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidosPrincipal extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final int REQUEST_CODE_AGREGAR_PRODUCTO = 1001;
    public static final int REQUEST_CODE_DETALLE_ACTIVITY = 1002;

    ListView lvProductos;
    ArrayAdapter<Producto> adapter;
    List<Producto> productos;
    DbDataSource dataSource;

    EditText etBuscador;
    Button btn_buscar;
    Button btn_cerrar_Sesion;


    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos_principal);
        setTitle("Lista de Productos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvProductos = findViewById(R.id.lvProductos);


        firebaseAuth = FirebaseAuth.getInstance();
        btn_cerrar_Sesion = findViewById(R.id.btn_cerrarSesion);


        dataSource = new DbDataSource(this);
        dataSource.openDB();
        productos = dataSource.obtenerProductos();
        dataSource.closeDB();


        adapter = new ProductoAdapter(this, R.layout.productos_item, productos);
        lvProductos.setAdapter(adapter);

        lvProductos.setOnItemClickListener(this);


        //etBuscador = findViewById(R.id.et_buscar);
        //btn_buscar = findViewById(R.id.btn_buscar);
        /*
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSource.openDB();
                dataSource.obtenerProductos().

                //productos = dataSource.buscarProductos(etBuscador.getText().toString());
                //dataSource.closeDB();
                //etBuscador.setText("");
            }
        });*/

        btn_cerrar_Sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singOut(view);
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Producto producto = productos.get(i);
        String nombre = producto.getNombre();//nombre producto
        Log.i("MainActivity", "Nombre: " + nombre);
        Toast.makeText(this, "Click en item " + i, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,DetalleActivity.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("producto", producto);


        startActivityForResult(intent, REQUEST_CODE_DETALLE_ACTIVITY);
        actualizarLista();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_pedidos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_agregar_producto:
                Intent intent = new Intent(this, AgregarProductoActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AGREGAR_PRODUCTO);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_AGREGAR_PRODUCTO && resultCode == 1){
            Log.i("ListaPedidosPrincipal", "Actualizar el ListView");
            actualizarLista();
        }
        if(requestCode ==  REQUEST_CODE_DETALLE_ACTIVITY && resultCode == 1){
            actualizarLista();
        }
        if (requestCode == REQUEST_CODE_DETALLE_ACTIVITY && resultCode == -1){
            //actualizar
        }
    }

    public void actualizarLista(){
        dataSource.openDB();
        productos = dataSource.obtenerProductos();
        dataSource.closeDB();

        adapter.clear();
        adapter.addAll(productos);
        adapter.notifyDataSetChanged();
    }



    public void singOut(View view){
        firebaseAuth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}