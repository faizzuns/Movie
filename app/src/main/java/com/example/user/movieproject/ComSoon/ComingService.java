package com.example.user.movieproject.ComSoon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 31/05/2017.
 */

public interface ComingService {
    @GET("upcoming?")
    Call<ComingSoonModel> getComingMovie(@Query("page") int page,@Query("api_key") String api_key);
}
