package com.example.proyectofinal.models;

import java.io.Serializable;

public class Producto implements Serializable {

    long id;
    String nombre;
    String nombre_dest;
    String tipo_prod;
    String telefono;
    String Direccion;
    String estado;
    String precio;
    String fecha_ent;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_dest() {
        return nombre_dest;
    }

    public void setNombre_dest(String nombre_dest) {
        this.nombre_dest = nombre_dest;
    }

    public String getTipo_prod() {
        return tipo_prod;
    }

    public void setTipo_prod(String tipo_prod) {
        this.tipo_prod = tipo_prod;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFecha_ent() {
        return fecha_ent;
    }

    public void setFecha_ent(String fecha_ent) {
        this.fecha_ent = fecha_ent;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nombre_dest='" + nombre_dest + '\'' +
                ", tipo_prod='" + tipo_prod + '\'' +
                ", telefono='" + telefono + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", estado='" + estado + '\'' +
                ", precio='" + precio + '\'' +
                ", fecha_ent='" + fecha_ent + '\'' +
                '}';
    }
}
