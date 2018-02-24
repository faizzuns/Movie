package com.example.user.movieproject.TopRated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 01/06/2017.
 */

public interface RatedService {
    @GET("top_rated?")
    Call<TopRatedModel> getTopRatedMovie(@Query("page") int page,@Query("api_key") String api_key);
}
