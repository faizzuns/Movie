package com.example.user.movieproject;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by User on 06/06/2017.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
