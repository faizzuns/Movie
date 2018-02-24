package com.example.user.movieproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 31/05/2017.
 */

public class ApiHelper {
    private static String BASE_URL = "https://api.themoviedb.org/3/movie/";

    static Retrofit retrofit = null;

    public static Retrofit client() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
