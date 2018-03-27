package com.avans2018.klasd.cineapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain.Movie;

// Activity voor detailscherm bij onItemClick in MainActivity
public class DetailActivity extends AppCompatActivity {
    private final static String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra(MainActivity.CLICKED_MOVIE);

    }
}
