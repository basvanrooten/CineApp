package com.avans2018.klasd.cineapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

        // Intent met data van film vanuit MainActivity
        Intent intent = getIntent();
        Movie clickedMovie = (Movie) intent.getSerializableExtra(MainActivity.CLICKED_MOVIE);

        // TextViews + ImageView
        TextView detailActivityPageHeader = (TextView) findViewById(R.id.detailFilmTitle);

        ImageView detailImageView = (ImageView) findViewById(R.id.detailFilmImg);
        TextView detailActivityIMDB = (TextView) findViewById(R.id.detailFilmIMDB);
        TextView detailActivityPlaytime = (TextView) findViewById(R.id.detailFilmPlaytime);
        TextView detailActivityFilmAge = (TextView) findViewById(R.id.detailFilmAge);

        TextView detailCommentHeader = (TextView) findViewById(R.id.detailFilmDescriptionHeader);
        TextView detailCommentContent = (TextView) findViewById(R.id.detailFilmDescriptionContent);


        TextView detailFilmTimesHeader = (TextView) findViewById(R.id.detailFilmTimesHeader);
        TextView detailFilmTimesContent = (TextView) findViewById(R.id.detailFilmTimesContent);

        // Picasso voor invullen ImageView
        String imageUrl = "https://d3fa68hw0m2vcc.cloudfront.net/e3a/91155935.jpeg";    // placeholder
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(detailImageView);

    }
}
