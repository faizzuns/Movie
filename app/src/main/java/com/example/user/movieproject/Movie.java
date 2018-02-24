package com.example.user.movieproject;

/**
 * Created by User on 15/05/2017.
 */

public class Movie {
    int id;
    String movie;
    String synopsis;
    String date;
    String genre;
    double rating;
    String judul;


    public Movie(){

    }

    public Movie(String movie){
        this.movie=movie;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getMovie(){
        return movie;
    }

    public void setMovie(String movie){
        this.movie=movie;
    }

    public String getSynopsis(){
        return synopsis;
    }

    public void setSynopsis(String synopsis){
        this.synopsis=synopsis;
    }
    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }
    public double getRating(){
        return rating;
    }

    public void setRating(double rating){
        this.rating=rating;
    }
    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul=judul;
    }


}
