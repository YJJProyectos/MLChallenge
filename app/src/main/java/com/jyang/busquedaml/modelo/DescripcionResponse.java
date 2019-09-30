package com.jyang.busquedaml.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DescripcionResponse {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String titulo;

    @SerializedName("price")
    @Expose
    private Double precio;

    @SerializedName("thumbnail")
    @Expose
    private String imagen;

    @SerializedName("initial_quantity")
    @Expose
    private int cantidad_inicial;

    @SerializedName("available_quantity")
    @Expose
    private int cantidad_disponible;

    @SerializedName("sold_quantity")
    @Expose
    private int cantidad_vendido;

    @SerializedName("pictures")
    @Expose
    private List<ImagenResponse> imagenes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad_inicial() {
        return cantidad_inicial;
    }

    public void setCantidad_inicial(int cantidad_inicial) {
        this.cantidad_inicial = cantidad_inicial;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public int getCantidad_vendido() {
        return cantidad_vendido;
    }

    public void setCantidad_vendido(int cantidad_vendido) {
        this.cantidad_vendido = cantidad_vendido;
    }
}
