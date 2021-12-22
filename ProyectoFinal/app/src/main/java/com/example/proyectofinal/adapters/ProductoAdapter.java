package com.example.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal.R;
import com.example.proyectofinal.models.Producto;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    Context context;
    List<Producto> objects;

    public ProductoAdapter(@NonNull Context context, int resource, @NonNull List<Producto> objects) {
        super(context, resource, objects);

        this.context = context;
        this.objects = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Producto producto = objects.get(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.productos_item, null);
        TextView tvNombre= view.findViewById(R.id.tvNombreProductoItem);
        TextView tvDireccion = view.findViewById(R.id.tvDirecciónItem);
        TextView tvEstado = view.findViewById(R.id.tvEstadoItem);

        String nombre = producto.getNombre();
        String direccion = producto.getDireccion();
        String estado = producto.getEstado();

        tvNombre.setText(nombre);
        tvDireccion.setText(direccion);
        tvEstado.setText(estado);

        return view;
    }


}
