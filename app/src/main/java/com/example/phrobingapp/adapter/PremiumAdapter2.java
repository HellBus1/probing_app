package com.example.phrobingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.phrobingapp.R;
import com.example.phrobingapp.pojo.iklan.Iklan;
import com.example.phrobingapp.tools.Converter;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class PremiumAdapter2 extends SliderViewAdapter<PremiumAdapter2.SliderViewHolder> {

    private List<Iklan> list_iklan;
    public PremiumAdapter2(List<Iklan> list_pelanggan){
        this.list_iklan = list_pelanggan;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_two, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        Iklan iklan = list_iklan.get(position);
        Glide.with(viewHolder.itemView)
                .load(Converter.base64Decoder(iklan.getPathGambar()))
                .into(viewHolder.promo);
    }

    @Override
    public int getCount() {
        return list_iklan.size();
    }

    static class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView promo;
        SliderViewHolder(View itemView) {
            super(itemView);
            promo = itemView.findViewById(R.id.petuah2);
        }
    }
}
