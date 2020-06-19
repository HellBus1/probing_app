package com.example.phrobingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phrobingapp.adapter.PremiumAdapter;
import com.example.phrobingapp.adapter.PremiumAdapter1;
import com.example.phrobingapp.adapter.PremiumAdapter2;
import com.example.phrobingapp.databinding.ActivityMainBinding;
import com.example.phrobingapp.fragments.Existing;
import com.example.phrobingapp.menu.Input2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    void init() {
        binding.layananPremiumSlider.setSliderAdapter(new PremiumAdapter());
        binding.layananPremiumNew.setSliderAdapter(new PremiumAdapter1());
        binding.tarifTegangan.setSliderAdapter(new PremiumAdapter2());
        binding.tombolProbing.setOnClickListener(this);
        binding.tombolStatusProbing.setOnClickListener(this);

        binding.nameP.setText(sharedPreferenceManager.getSp_nama());
        binding.emailP.setText(sharedPreferenceManager.getSp_email());
        binding.logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tombol_probing:{
                startActivity(new Intent(MainActivity.this, Input2.class));
            }break;
            case R.id.tombol_status_probing:{
                startActivity(new Intent(MainActivity.this, Existing.class));
            }break;
            case R.id.logout_btn:{
                sharedPreferenceManager.saveSPBoolean(SharedPreferenceManager.sp_sudah, false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }break;
        }
    }
}
