package com.example.phrobingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {
    public static final String sp_nama = "sp_nama";
    public static final String sp_email = "sp_email";
    public static final String sp_role = "sp_role";
    public static final String sp_sudah = "sp_sudah";


    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    public SharedPreferenceManager(Context context){
        sharedPreferences = context.getSharedPreferences("Login_Pref",Context.MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
    }

    public void saveSpString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSp_nama() {
        return sharedPreferences.getString(sp_nama, "");
    }

    public String getSp_email() {
        return sharedPreferences.getString(sp_email, "");
    }

    public String getSp_role() {
        return sharedPreferences.getString(sp_role, "");
    }

    public boolean getSp_sudah() {
        return sharedPreferences.getBoolean(sp_sudah, false);
    }

    public void clear(){
        spEditor.clear().commit();
    }
}
