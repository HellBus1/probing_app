package com.example.phrobingapp.login_serv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("penyulang")
    @Expose
    private List<Penyulang> penyulang = null;
    @SerializedName("unit")
    @Expose
    private List<Unit> unit = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Penyulang> getPenyulang() {
        return penyulang;
    }

    public void setPenyulang(List<Penyulang> penyulang) {
        this.penyulang = penyulang;
    }

    public List<Unit> getUnit() {
        return unit;
    }

    public void setUnit(List<Unit> unit) {
        this.unit = unit;
    }
}
