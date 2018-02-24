package com.example.user.movieproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.user.movieproject.adapter.MovieAdapter;
import com.example.user.movieproject.adapter.TheaterAdapter;

import java.util.ArrayList;

public class AllTheater extends AppCompatActivity {
    ArrayList<Theater> listTheater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_theater);

        getSupportActionBar().setTitle("All Theaters");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvTheater = (RecyclerView)findViewById(R.id.my_recycler_view);
        rvTheater.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvTheater.setLayoutManager(llm);

        listTheater=mockDataTheater();
        TheaterAdapter adapterTheater= new TheaterAdapter(listTheater);
        rvTheater.setAdapter(adapterTheater);

    }

    private ArrayList<Theater> mockDataTheater(){
        ArrayList<Theater> list=new ArrayList<>();
        Theater theater;

        int[] gambar=new int[]{
                R.drawable.bioskop,
                R.drawable.bioskop,
                R.drawable.bioskop,
                R.drawable.bioskop,
                R.drawable.bioskop
        };
        String[] Tempat = new String[]{
                "Bandung Indah Plaza",
                "Bandung Electronic City",
                "Bandung Bandung",
                "Paris Van Java",
                "Balubur Town Square"
        };
        String[] Alamat = new String[]{
                "Jl. Merdeka No.56, Citarum, Bandung Wetan, Kota Bandung, Jawa Barat 40115",
                "Jl. Purnawarman, Babakan Ciamis, Sumur Bandung, Kota Bandung, Jawa Barat",
                "Jl. Dipatiukur",
                "Jl. Sukajadi No. 131 - 139, Cipedes, Sukajadi, Kota Bandung, Jawa Barat 40162",
                "Jl. Taman Sari, Tamansari, Bandung Wetan, Kota Bandung, Jawa Barat 40116"
        };

        for (int g=0;g<gambar.length;g++){
            theater=new Theater(gambar[g],Tempat[g],Alamat[g]);
            list.add(theater);
        }

        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
