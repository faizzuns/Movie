package com.example.user.movieproject.topMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 31/05/2017.
 */

public interface TopService {
    @GET("popular?")
    Call<TopMovieModel> getTopMovie(@Query("api_key") String api_key);
}
