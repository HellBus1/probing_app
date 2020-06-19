package com.example.phrobingapp.pojo.iklan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Iklan {
    @SerializedName("path_gambar")
    @Expose
    private String pathGambar;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("where_promote")
    @Expose
    private Integer wherePromote;

    public String getPathGambar() {
        return pathGambar;
    }

    public void setPathGambar(String pathGambar) {
        this.pathGambar = pathGambar;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getWherePromote() {
        return wherePromote;
    }

    public void setWherePromote(Integer wherePromote) {
        this.wherePromote = wherePromote;
    }
}
