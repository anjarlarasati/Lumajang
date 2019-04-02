package com.laras.lumajang.Fragment.Home;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laras.lumajang.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by laras on 7/9/2018.
 */

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder>{
    private ArrayList<DataWisata> datalist;
    public WisataAdapter(ArrayList<DataWisata> datalist){
        this.datalist = datalist;
    }
    @Override
    public WisataViewHolder onCreateViewHolder (ViewGroup parent , int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_wisata, parent, false);
        return new WisataViewHolder(view);
    }
    @Override
    public void onBindViewHolder(WisataViewHolder holder , int position){
        holder.txtNama.setText(datalist.get(position).getNama());
        Picasso.get()
                .load(datalist.get(position).getGambar())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deskripsi = new Intent();

            }
        });
        holder.txtAlamat.setText(datalist.get(position).getAlamat());

    }
    @Override
    public int getItemCount(){
        return (datalist !=null) ? datalist.size() : 0;
    }

    public class WisataViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama,txtAlamat,txtDeskripsi ;
        private ImageView img;


        public WisataViewHolder (View itemView){
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            img=(ImageView) itemView.findViewById(R.id.img_wisata);
            txtAlamat=(TextView) itemView.findViewById(R.id.txt_lokasi);
            txtDeskripsi=(TextView) itemView.findViewById(R.id.textDeskripsi);

        }
    }
}
