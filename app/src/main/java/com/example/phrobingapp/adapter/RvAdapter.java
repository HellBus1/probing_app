package com.example.phrobingapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phrobingapp.R;
import com.example.phrobingapp.databinding.CardItemBinding;
import com.example.phrobingapp.menu.Input;
import com.example.phrobingapp.pojo.Pelanggan;
import com.example.phrobingapp.pojo.PelangganSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends
        RecyclerView.Adapter<RvAdapter.ViewHolder>  {
    Activity context;
    List<Pelanggan> list_pelanggan;
    public RvAdapter(Activity context, List<Pelanggan> list_pelanggan){
        this.context = context;
        this.list_pelanggan = list_pelanggan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.card_item, parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pelanggan pelanggan = list_pelanggan.get(position);
        holder.namausaha.setText(pelanggan.getNamaUsaha());
        holder.idPel.setText(String.valueOf(pelanggan.getPelangganId()));
        holder.idPLN.setText(pelanggan.getIdPlnPelanggan());
        holder.alamatusaha.setText(pelanggan.getAlamat());
    }

    @Override
    public int getItemCount() {
        return list_pelanggan.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView idPel;
        TextView idPLN;
        TextView namausaha;
        TextView alamatusaha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idPel = itemView.findViewById(R.id.idpel);
            idPLN = itemView.findViewById(R.id.idpln);
            namausaha = itemView.findViewById(R.id.nama);
            alamatusaha = itemView.findViewById(R.id.alamatpel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Pelanggan a = list_pelanggan.get(position);
                        Intent i = new Intent(context, Input.class);
                        PelangganSerializable temp = new PelangganSerializable(
                                a.getPelangganId(),
                                a.getRekapId(),
                                a.getIdPlnPelanggan(),
                                a.getNamaUsaha(),
                                a.getAlamat(),
                                a.getJenisUsaha(),
                                a.getTelefon(),
                                a.getFax(),
                                a.getContactPerson(),
                                a.getEmail(),
                                a.getTipeIndustri(),
                                a.getTarif(),
                                a.getDayaBeli(),
                                a.getBulan(),
                                a.getTahun(),
                                a.getUlpUnit()
                        );
                        i.putExtra(Input.KEY_PINDAH,  temp);
                        context.startActivity(i);
                    }
                }
            });
        }
    }
}
