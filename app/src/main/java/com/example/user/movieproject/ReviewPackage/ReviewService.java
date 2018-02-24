package com.example.user.movieproject.ReviewPackage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 01/06/2017.
 */

public interface ReviewService {
    @GET("{movie_id}/reviews?")
    Call<ReviewModel> getReview(@Path("movie_id") int id, @Query("api_key") String api_key);
}
