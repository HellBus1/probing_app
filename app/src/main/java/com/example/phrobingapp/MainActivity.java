package com.example.phrobingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phrobingapp.adapter.PremiumAdapter;
import com.example.phrobingapp.adapter.PremiumAdapter1;
import com.example.phrobingapp.adapter.PremiumAdapter2;
import com.example.phrobingapp.databinding.ActivityMainBinding;
import com.example.phrobingapp.menu.Harian;
import com.example.phrobingapp.menu.Input;
import com.example.phrobingapp.menu.Riwayat;
import com.example.phrobingapp.menu.Riwayat_satu;
import com.example.phrobingapp.menu.Status;

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
        binding.tombolPhrobing.setOnClickListener(this);
        binding.tombolHarian.setOnClickListener(this);
        binding.tombolRiwayat.setOnClickListener(this);
        binding.tombolStatusPhrobing.setOnClickListener(this);

        binding.nameP.setText(sharedPreferenceManager.getSp_nama());
        binding.emailP.setText(sharedPreferenceManager.getSp_email());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tombol_phrobing:{
                startActivity(new Intent(MainActivity.this, Input.class));
            }break;
            case R.id.tombol_riwayat:{
                showAlertDialog();
            }break;
            case R.id.tombol_harian:{
                startActivity(new Intent(MainActivity.this, Harian.class));
            }break;
            case R.id.tombol_status_phrobing:{
                startActivity(new Intent(MainActivity.this, Status.class));
            }break;
            case R.id.logout_btn:{
                sharedPreferenceManager.saveSPBoolean(SharedPreferenceManager.sp_sudah, false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }break;
        }
    }

    private void showAlertDialog(){
        DialogRiwayat dr = new DialogRiwayat(MainActivity.this);
        dr.show(getSupportFragmentManager(), "popover");
    }

    public static class DialogRiwayat extends DialogFragment {
        private CardView per_unit, riwayat;
        private Context context;
        DialogRiwayat(Context context){
            this.context = context;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.riwayat_fragments, container, false);
            per_unit = view.findViewById(R.id.perunit);
            riwayat = view.findViewById(R.id.satuan);

            init();
            return view;
        }

        private void init(){
            per_unit.setOnClickListener((View v) -> {
                startActivity(new Intent(context, Riwayat.class));
                this.dismiss();
            });
            riwayat.setOnClickListener((View v) -> {
                startActivity(new Intent(context, Riwayat_satu.class));
                this.dismiss();
            });
        }
    }
}
