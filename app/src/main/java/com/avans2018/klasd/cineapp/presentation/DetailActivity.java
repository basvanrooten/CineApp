package com.avans2018.klasd.cineapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain.Movie;
import com.squareup.picasso.Picasso;

// Activity voor detailscherm bij onItemClick in MainActivity
public class DetailActivity extends AppCompatActivity {
    private final static String TAG = "DetailActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra(MainActivity.CLICKED_MOVIE);

        ImageView imageView = findViewById(R.id.detailFilmImg);
        String imageUrl = "https://d3fa68hw0m2vcc.cloudfront.net/e3a/91155935.jpeg";    // placeholder
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(imageView);

    }
}
