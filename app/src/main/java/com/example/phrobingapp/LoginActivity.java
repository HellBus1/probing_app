package com.example.phrobingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phrobingapp.connection.ApiInterface;
import com.example.phrobingapp.connection.RetrofitBuilder;
import com.example.phrobingapp.databinding.ActivityLoginBinding;
import com.example.phrobingapp.login_serv.Data;
import com.example.phrobingapp.login_serv.Login_pojo;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private ActivityLoginBinding binding;
    private SharedPreferenceManager sharedPreferenceManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sharedPreferenceManager = new SharedPreferenceManager(this);
//        if (sharedPreferenceManager.getSp_sudah()){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
        binding.btnLogin.setOnClickListener(this);
//        binding.btnLogin.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String email = binding.editUsername.getText().toString();
            String password = binding.editPassword.getText().toString();
            login_meth(email, password);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void login_meth(String email, String password){
        binding.loadingBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RetrofitBuilder.getClient().create(ApiInterface.class);
        Call<Login_pojo> caller = apiInterface.login_conn(email, password);
        caller.enqueue(new Callback<Login_pojo>() {
            @Override
            public void onResponse(Call<Login_pojo> call, Response<Login_pojo> response) {
                if (response.isSuccessful()){
                    binding.loadingBar.setVisibility(View.GONE);
                    try{
                        Login_pojo poj = response.body();
                        if((poj != null ? poj.getSuccess() : null) != null && poj.getData() != null){
                            session(poj.getData());
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    binding.loadingBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Login_pojo> call, @NotNull Throwable t) {
                binding.loadingBar.setVisibility(View.GONE);
                t.printStackTrace();
                Toast.makeText(LoginActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void session(Data data){
        sharedPreferenceManager.saveSpString(SharedPreferenceManager.sp_nama, data.getName());
        sharedPreferenceManager.saveSpString(SharedPreferenceManager.sp_email, data.getEmail());
        sharedPreferenceManager.saveSpString(SharedPreferenceManager.sp_role, data.getRole());
        sharedPreferenceManager.saveSPBoolean(SharedPreferenceManager.sp_sudah, true);

        Konstanta.units = data.getUnit();
        Konstanta.penyulangs = data.getPenyulang();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                ImageView view = (ImageView) v;
                //overlay is black with transparency of 0x77 (119)
                view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                view.invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                ImageView view = (ImageView) v;
                //clear the overlay
                view.getDrawable().clearColorFilter();
                view.invalidate();
                break;
            }
        }

        return false;
    }
}
