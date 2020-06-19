package com.example.phrobingapp.pojo.iklan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListIklan {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<Iklan> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<Iklan> getData() {
        return data;
    }

    public void setData(List<Iklan> data) {
        this.data = data;
    }
}
