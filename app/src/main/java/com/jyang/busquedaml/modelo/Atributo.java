package com.jyang.busquedaml.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atributo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String nombre;

    @SerializedName("value_name")
    @Expose
    private String valor;

    @SerializedName("value_struct")
    @Expose
    private AtributoEstructura atributoEstructura;

    public AtributoEstructura getAtributoEstructura() {
        return atributoEstructura;
    }

    public void setAtributoEstructura(AtributoEstructura atributoEstructura) {
        this.atributoEstructura = atributoEstructura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
