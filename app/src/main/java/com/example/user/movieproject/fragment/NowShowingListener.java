package com.example.user.movieproject.fragment;

import com.example.user.movieproject.Movie;

import java.util.ArrayList;

/**
 * Created by User on 16/05/2017.
 */

public interface NowShowingListener {
    void onButtonDetailClick(int position, ArrayList<Movie> movie);
}
