package com.example.user.movieproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.movieproject.R;
import com.example.user.movieproject.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 15/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> listMovie;

    TopListener topListener;

    boolean isImageFitToScreen;

    public MovieAdapter(ArrayList<Movie> listMovie,TopListener listener){
        this.listMovie=listMovie;
        this.topListener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgmovie;
        public ViewHolder(View v){
            super(v);
            imgmovie=(ImageView)v.findViewById(R.id.top_movie_fim);
        }
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_movie_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Movie topmovie = listMovie.get(position);

        //holder.imgmovie.setImageResource(listMovie.get(position).getMovie());
        Picasso.with(holder.imgmovie.getContext()).load("https://image.tmdb.org/t/p/w500"+listMovie.get(position).getMovie()).into(holder.imgmovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topListener.onClickTop(position,listMovie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void refreshData(ArrayList<Movie> listTopMovie) {
        this.listMovie=listTopMovie;
        notifyDataSetChanged();
    }


}
