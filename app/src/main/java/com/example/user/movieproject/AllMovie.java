package com.example.user.movieproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.movieproject.ComSoon.ComingService;
import com.example.user.movieproject.ComSoon.ComingSoonModel;
import com.example.user.movieproject.ComSoon.ResultComingSoon;
import com.example.user.movieproject.NowShow.NowService;
import com.example.user.movieproject.NowShow.NowShowingModel;
import com.example.user.movieproject.NowShow.ResultNowShowing;
import com.example.user.movieproject.adapter.TabFragmentPagerAdapter;
import com.example.user.movieproject.fragment.NowShowingListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMovie extends AppCompatActivity  {
    private ViewPager pager;
    private TabLayout tabs;
    private LinearLayout ly;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);

        getSupportActionBar().setTitle("All Movie");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("tes", "bisa kok");



        pager = (ViewPager)findViewById(R.id.pager);
        tabs = (TabLayout)findViewById(R.id.tabs);
        ly = (LinearLayout)findViewById(R.id.layout_all);


        //set object adapter kedalam ViewPager
        pager.setAdapter( new TabFragmentPagerAdapter(getSupportFragmentManager()));



        //Manimpilasi sedikit untuk set TextColor pada Tab
        tabs.setTabTextColors(getResources().getColor(R.color.blackCow),
                getResources().getColor(android.R.color.white));

        //set tab ke ViewPager
        tabs.setupWithViewPager(pager);

        //konfigurasi Gravity Fill untuk Tab berada di posisi yang proposional
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
