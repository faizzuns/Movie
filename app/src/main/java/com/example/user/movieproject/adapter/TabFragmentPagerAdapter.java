package com.example.user.movieproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.movieproject.Movie;
import com.example.user.movieproject.fragment.ComingSoonFragment;
import com.example.user.movieproject.fragment.FavouriteFragment;
import com.example.user.movieproject.fragment.NowShowingFragment;
import com.example.user.movieproject.fragment.NowShowingListener;
import com.example.user.movieproject.fragment.TopRatedFragment;

import java.util.ArrayList;

/**
 * Created by User on 13/05/2017.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {


    //nama tab nya
    String[] title = new String[]{
            "Now Showing", "Coming Soon","Top Rated","Favourite"
    };


    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new NowShowingFragment();
                break;
            case 1:
                fragment = new ComingSoonFragment();
                break;
            case 2:
                fragment = new TopRatedFragment();
                break;
            case 3:
                fragment = new FavouriteFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }

}
