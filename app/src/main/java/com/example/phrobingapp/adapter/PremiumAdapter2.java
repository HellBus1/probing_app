package com.example.phrobingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.phrobingapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class PremiumAdapter2 extends SliderViewAdapter<PremiumAdapter2.SliderViewHolder> {

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_two, null);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        switch (position) {
            case 0:{
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.promo1)
                        .into(viewHolder.promo);
            }break;
            case 1:{
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.promo2)
                        .into(viewHolder.promo);
            }break;
            case 2:{
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.promo3)
                        .into(viewHolder.promo);
            }break;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    static class SliderViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView promo;
        SliderViewHolder(View itemView) {
            super(itemView);
            promo = itemView.findViewById(R.id.petuah2);
        }
    }
}
