package com.example.phrobingapp.login_serv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_pojo {
    private static Login_pojo singleInstance = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public static Login_pojo getInstance(){
        if(singleInstance == null){
            singleInstance = new Login_pojo();
        }
        return singleInstance;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

