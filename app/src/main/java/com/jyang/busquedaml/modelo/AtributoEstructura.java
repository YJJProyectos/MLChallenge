package com.jyang.busquedaml.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AtributoEstructura {

    @SerializedName("unit")
    @Expose
    private String unit;

    @SerializedName("number")
    @Expose
    private Double number;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
