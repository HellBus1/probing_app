package com.example.phrobingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.phrobingapp.adapter.PremiumAdapter;
import com.example.phrobingapp.adapter.PremiumAdapter1;
import com.example.phrobingapp.adapter.PremiumAdapter2;
import com.example.phrobingapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    void init() {
        binding.layananPremiumSlider.setSliderAdapter(new PremiumAdapter());
        binding.layananPremiumNew.setSliderAdapter(new PremiumAdapter1());
        binding.tarifTegangan.setSliderAdapter(new PremiumAdapter2());
    }
}
