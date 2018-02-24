package com.example.user.movieproject;

import android.widget.TextView;

/**
 * Created by User on 19/05/2017.
 */

public class Theater {
    int gambarTempat;
    String Tempat;
    String AlamatTempat;

    public Theater(int gambarTempat,String Tempat, String AlamatTempat){
        this.gambarTempat=gambarTempat;
        this.Tempat=Tempat;
        this.AlamatTempat=AlamatTempat;
    }

    public int getGambarTempat(){
        return gambarTempat;
    }

    public String getTempat(){
        return Tempat;
    }

    public String getAlamatTempat(){
        return  AlamatTempat;
    }
}
