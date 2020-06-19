package com.example.phrobingapp.login_serv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Penyulang {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_penyulang")
    @Expose
    private String namaPenyulang;
    @SerializedName("trafo")
    @Expose
    private Integer trafo;
    @SerializedName("gardu_induk")
    @Expose
    private String garduInduk;
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

    public String getNamaPenyulang() {
        return namaPenyulang;
    }

    public void setNamaPenyulang(String namaPenyulang) {
        this.namaPenyulang = namaPenyulang;
    }

    public Integer getTrafo() {
        return trafo;
    }

    public void setTrafo(Integer trafo) {
        this.trafo = trafo;
    }

    public String getGarduInduk() {
        return garduInduk;
    }

    public void setGarduInduk(String garduInduk) {
        this.garduInduk = garduInduk;
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
