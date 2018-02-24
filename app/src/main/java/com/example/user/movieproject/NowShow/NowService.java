package com.example.user.movieproject.NowShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 31/05/2017.
 */

public interface NowService {
    @GET("now_playing?")
    Call<NowShowingModel> getNowMovie(@Query("page") int page,@Query("api_key") String api_key);

}
