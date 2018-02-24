package com.example.user.movieproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.movieproject.ApiHelper;
import com.example.user.movieproject.DetailMovie;
import com.example.user.movieproject.Movie;
import com.example.user.movieproject.NowShow.NowService;
import com.example.user.movieproject.NowShow.NowShowingModel;
import com.example.user.movieproject.NowShow.ResultNowShowing;
import com.example.user.movieproject.R;
import com.example.user.movieproject.adapter.TabFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowShowingFragment extends Fragment implements NowShowingListener {
    private GridView gv_nowShowing;

    ArrayList<Movie> listPosterMovie;
    NowShowingAdapter adapter;
    int x= 0;
    int count=0;
    int y;

    private SpotsDialog progressDialog;

    public NowShowingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_now_showing, container, false);
        count=0;

        listPosterMovie=new ArrayList<>();
        gv_nowShowing=(GridView)view.findViewById(R.id.grid_now_showing);
        progressDialog=new SpotsDialog(getContext(),"Load data...");
        adapter=new NowShowingAdapter(getContext(),listPosterMovie,this);
        gv_nowShowing.setAdapter(adapter);

        callNowShowingApi();

        gv_nowShowing.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("scroll1",firstVisibleItem+" "+visibleItemCount+" "+totalItemCount+" "+count);
                if (count==1){
                    x=totalItemCount;
                }
                //y=totalItemCount;
                if (firstVisibleItem+visibleItemCount==totalItemCount&&totalItemCount!=0&&x*count<=totalItemCount){
                    callNowShowingApi();
                }
                if (x*count==totalItemCount){
                    progressDialog.dismiss();
                }
            }
        });



        return view;
    }



    public void onButtonDetailClick(int position,ArrayList<Movie> movie){
        Intent intent =new Intent(getActivity(), DetailMovie.class);
        intent.putExtra("poster",movie.get(position).getMovie());
        intent.putExtra("judul",movie.get(position).getJudul());
        intent.putExtra("date",movie.get(position).getDate());
        intent.putExtra("synopsis",movie.get(position).getSynopsis());
        intent.putExtra("rating",movie.get(position).getRating());
        intent.putExtra("id",movie.get(position).getId());
        startActivity(intent);
    }

    private void callNowShowingApi() {
        progressDialog.show();

        count=count+1;
        Log.d("scroll",String.valueOf(count));
        NowService service = ApiHelper.client().create(NowService.class);

        Call<NowShowingModel> call = service.getNowMovie(count,"d180ce17a02d3acb8fc79d2adc16fe96");

        call.enqueue(new Callback<NowShowingModel>() {
            @Override
            public void onResponse(Call<NowShowingModel> call, Response<NowShowingModel> response) {
                List<ResultNowShowing> nowList = response.body().getResults();

                for (ResultNowShowing rSult : nowList){
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

                    listPosterMovie.add(movie);
                }
                adapter.refreshDataM(listPosterMovie);

            }

            @Override
            public void onFailure(Call<NowShowingModel> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("tes", t.toString());

            }
        });


    }
}
