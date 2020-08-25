package com.example.phrobingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.phrobingapp.adapter.PremiumAdapter;
import com.example.phrobingapp.adapter.PremiumAdapter1;
import com.example.phrobingapp.adapter.PremiumAdapter2;
import com.example.phrobingapp.connection.ApiInterface;
import com.example.phrobingapp.connection.RetrofitBuilder;
import com.example.phrobingapp.databinding.ActivityMainBinding;
import com.example.phrobingapp.fragments.Existing;
import com.example.phrobingapp.menu.Baru;
import com.example.phrobingapp.pojo.iklan.GetListIklan;
import com.example.phrobingapp.pojo.iklan.Iklan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private SharedPreferenceManager sharedPreferenceManager;
    private List<Iklan> list_iklan;
    private List<Iklan> promoPremium = new ArrayList<>();
    private List<Iklan> premium = new ArrayList<>();
    private List<Iklan> menengah = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    void init() {
        binding.loading1.setVisibility(View.VISIBLE);
        binding.loading2.setVisibility(View.VISIBLE);
        binding.loading3.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RetrofitBuilder.getClient().create(ApiInterface.class);
        Call<GetListIklan> caller = apiInterface.getIklan();
        caller.enqueue(new Callback<GetListIklan>() {
            @Override
            public void onResponse(Call<GetListIklan> call, Response<GetListIklan> response) {
                if(response.isSuccessful()){
                    binding.loading1.setVisibility(View.GONE);
                    binding.loading2.setVisibility(View.GONE);
                    binding.loading3.setVisibility(View.GONE);
                    GetListIklan listing = response.body();
                    if (listing != null && listing.getSuccess() && listing.getPesan().equals("berhasil")) {
                        list_iklan = listing.getData();
                        for (Iklan iklan : list_iklan) {
                            if(iklan.getIsActive() == 1){
                                if(iklan.getWherePromote() == 1){
                                    promoPremium.add(iklan);
                                }else if(iklan.getWherePromote() == 2){
                                    premium.add(iklan);
                                }else if(iklan.getWherePromote() == 3){
                                    menengah.add(iklan);
                                }
                            }
                        }
                        binding.layananPremiumSlider.setSliderAdapter(new PremiumAdapter(promoPremium));
                        binding.layananPremiumNew.setSliderAdapter(new PremiumAdapter1(premium));
                        binding.tarifTegangan.setSliderAdapter(new PremiumAdapter2(menengah));
                    }
                }else{
                    binding.loading1.setVisibility(View.GONE);
                    binding.loading2.setVisibility(View.GONE);
                    binding.loading3.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetListIklan> call, Throwable t) {
                binding.loading1.setVisibility(View.GONE);
                binding.loading2.setVisibility(View.GONE);
                binding.loading3.setVisibility(View.GONE);
                Log.e("Kegagalan ", t.getMessage());
                Toast.makeText(MainActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
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
                startActivity(new Intent(MainActivity.this, Baru.class));
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
