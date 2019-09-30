package com.jyang.busquedaml.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImagenResponse {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("secure_url")
    @Expose
    private String url_segura;

    @SerializedName("size")
    @Expose
    private String tamanio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_segura() {
        return url_segura;
    }

    public void setUrl_segura(String url_segura) {
        this.url_segura = url_segura;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }
}
