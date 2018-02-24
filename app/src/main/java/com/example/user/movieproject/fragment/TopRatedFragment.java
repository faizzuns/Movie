package com.example.user.movieproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.example.user.movieproject.ApiHelper;
import com.example.user.movieproject.ComSoon.ComingService;
import com.example.user.movieproject.DetailMovie;
import com.example.user.movieproject.Movie;
import com.example.user.movieproject.R;
import com.example.user.movieproject.TopRated.RatedService;
import com.example.user.movieproject.TopRated.ResultTopRated;
import com.example.user.movieproject.TopRated.TopRatedModel;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment implements NowShowingListener{
    private GridView gvTopRated;
    NowShowingAdapter adapter;

    ArrayList<Movie> listTopRated;
    int count = 0;
    int x;

    private SpotsDialog progressDialog;

    public TopRatedFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_rated, container, false);
        count=0;

        listTopRated=new ArrayList<>();

        gvTopRated = (GridView)view.findViewById(R.id.grid_top_rated);
        progressDialog=new SpotsDialog(getContext(),"Load data...");
        adapter =new NowShowingAdapter(getContext(),listTopRated,this);
        gvTopRated.setAdapter(adapter);
        callTopRatedApi();

        gvTopRated.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("scroll3",firstVisibleItem+" "+visibleItemCount+" "+totalItemCount+" "+count);
                if (count==1){
                    x=firstVisibleItem+visibleItemCount;
                }
                if (firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount!=0&&x*count<=totalItemCount){
                    callTopRatedApi();
                }
                if (x*count==totalItemCount){
                    progressDialog.dismiss();
                }
            }
        });

        return view;
    }




    private void callTopRatedApi() {
        progressDialog.show();
        count=count+1;
        Log.d("page",String.valueOf(count));
        RatedService service = ApiHelper.client().create(RatedService.class);

        Call<TopRatedModel> call = service.getTopRatedMovie(count,"d180ce17a02d3acb8fc79d2adc16fe96");
        call.enqueue(new Callback<TopRatedModel>() {
            @Override
            public void onResponse(Call<TopRatedModel> call, Response<TopRatedModel> response) {
                List<ResultTopRated> ratList = response.body().getResults();

                for (ResultTopRated rSult : ratList){
                    String date = rSult.getReleaseDate();
                    String poster = rSult.getPosterPath();
                    String synopsis = rSult.getOverview();
                    String judul = rSult.getTitle();
                    double rating = rSult.getVoteAverage();
                    int id = rSult.getId();

                    Movie movie = new Movie();
                    movie.setId(id);
                    movie.setDate(date);
                    movie.setMovie(poster);
                    movie.setSynopsis(synopsis);
                    movie.setJudul(judul);
                    movie.setRating(rating);

                    listTopRated.add(movie);

                }
                adapter.refreshDataM(listTopRated);


            }

            @Override
            public void onFailure(Call<TopRatedModel> call, Throwable t) {

            }
        });
    }

    public void onButtonDetailClick(int position,ArrayList<Movie> listPosterComingSoon){
        Intent intent =new Intent(getActivity(), DetailMovie.class);
        intent.putExtra("poster",listPosterComingSoon.get(position).getMovie());
        intent.putExtra("judul",listPosterComingSoon.get(position).getJudul());
        intent.putExtra("date",listPosterComingSoon.get(position).getDate());
        intent.putExtra("synopsis",listPosterComingSoon.get(position).getSynopsis());
        intent.putExtra("rating",listPosterComingSoon.get(position).getRating());
        intent.putExtra("id",listPosterComingSoon.get(position).getId());
        startActivity(intent);
    }

}
