package com.example.phrobingapp.login_serv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unit {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_pln_unit")
    @Expose
    private String idPlnUnit;
    @SerializedName("ulp")
    @Expose
    private String ulp;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPlnUnit() {
        return idPlnUnit;
    }

    public void setIdPlnUnit(String idPlnUnit) {
        this.idPlnUnit = idPlnUnit;
    }

    public String getUlp() {
        return ulp;
    }

    public void setUlp(String ulp) {
        this.ulp = ulp;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
