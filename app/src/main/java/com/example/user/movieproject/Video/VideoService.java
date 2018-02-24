package com.example.user.movieproject.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 01/06/2017.
 */

public interface VideoService {
    @GET("{movie_id}/videos?")
    Call<VideoModel> getVideo(@Path("movie_id") int id, @Query("api_key") String api_key);
}
