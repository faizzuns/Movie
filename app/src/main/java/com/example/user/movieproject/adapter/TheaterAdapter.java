package com.example.user.movieproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.movieproject.R;
import com.example.user.movieproject.Theater;

import java.util.ArrayList;

/**
 * Created by User on 19/05/2017.
 */

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.ViewHolder> {

    ArrayList<Theater> listTheater;

    public TheaterAdapter(ArrayList<Theater> listTheater){
        this.listTheater=listTheater;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBioskop;
        TextView txtTempat,txtAlamat;

        public ViewHolder(View v){
            super(v);
            imgBioskop=(ImageView)v.findViewById(R.id.img_bioskop);
            txtTempat=(TextView)v.findViewById(R.id.txt_tempat);
            txtAlamat=(TextView)v.findViewById(R.id.alamat_bioskop);
        }
    }

    @Override
    public TheaterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_theater_item,parent, false);

        ViewHolder vh2= new ViewHolder(v);
        return  vh2;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Theater theater=listTheater.get(position);

        holder.imgBioskop.setImageResource(theater.getGambarTempat());
        holder.txtTempat.setText(theater.getTempat());
        holder.txtAlamat.setText(theater.getAlamatTempat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listTheater.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
