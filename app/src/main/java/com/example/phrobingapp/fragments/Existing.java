package com.example.phrobingapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.phrobingapp.R;
import com.example.phrobingapp.SharedPreferenceManager;
import com.example.phrobingapp.adapter.RvAdapter;
import com.example.phrobingapp.connection.ApiInterface;
import com.example.phrobingapp.connection.RetrofitBuilder;
import com.example.phrobingapp.databinding.ActivityExistingBinding;
import com.example.phrobingapp.pojo.GetListTO;
import com.example.phrobingapp.pojo.Pelanggan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Existing extends AppCompatActivity {
    private RvAdapter adapter;
    private List<Pelanggan> list = new ArrayList<>();
    ActivityExistingBinding binding;
    private SharedPreferenceManager sharedPreferenceManager;

    public Existing() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_existing);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        init();
    }

    private void init(){
        binding.loadings.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RetrofitBuilder.getClient().create(ApiInterface.class);
        Call<GetListTO> caller = apiInterface.getTo(Integer.parseInt(sharedPreferenceManager.getSp_user_id()));
        caller.enqueue(new Callback<GetListTO>() {
            @Override
            public void onResponse(Call<GetListTO> call, Response<GetListTO> response) {
                if(response.isSuccessful()){
                    binding.loadings.setVisibility(View.GONE);
                    GetListTO amp = response.body();
                    if (amp != null && amp.getSuccess() && amp.getMessage().equals("TO berhasil didapat")) {
                        list.addAll(amp.getData());
                        Collections.sort(list, new Comparator<Pelanggan>() {
                            @Override
                            public int compare(Pelanggan o1, Pelanggan o2) {
                                return o1.getPelangganId().compareTo(o2.getPelangganId());
                            }
                        });
                        adapter = new RvAdapter(Existing.this, list);
                        binding.rvTemp.setLayoutManager(new LinearLayoutManager(Existing.this));
                        binding.rvTemp.setAdapter(adapter);
                    }
                }else{
                    binding.loadings.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetListTO> call, Throwable t) {
                binding.loadings.setVisibility(View.GONE);
                Log.e("Kegagalam ", t.getMessage());
            }
        });
    }
}
