package com.avans2018.klasd.cineapp.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic.OnItemClickListener;
import com.avans2018.klasd.cineapp.data_access_layer.MovieListTask;
import com.avans2018.klasd.cineapp.data_access_layer.MovieListener;
import com.avans2018.klasd.cineapp.domain.Movie;

import java.util.ArrayList;

// Filmoverzicht - Beginscherm van de app
public class MainActivity extends AppCompatActivity implements OnItemClickListener, MovieListener {
    private final static String TAG = "MainActivity";
    final static String CLICKED_MOVIE = "clickedMovie";

    private RecyclerView recyclerView;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter = new MovieListAdapter(MainActivity.this, movieList);

    // Test data zonder database
    Movie voorbeeld1 = new Movie();
    Movie voorbeeld2 = new Movie();
    Movie voorbeeld3 = new Movie();
    Movie voorbeeld4 = new Movie();
    Movie voorbeeld5 = new Movie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieListTask movieListTask = new MovieListTask(this);
        movieListTask.execute();

        // Test data zonder database
//            movieList.add(voorbeeld1);
//            movieList.add(voorbeeld2);
//            movieList.add(voorbeeld3);
//            movieList.add(voorbeeld4);
//            movieList.add(voorbeeld5);

        // RecyclerView voor het weergeven van lijst van films
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(int position) {
        // Klik logica voor meegeven film en opstarten DetailActivity
        Log.i(TAG, "onItemClick() called.");
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Movie clickedMovie = movieList.get(position);
        detailIntent.putExtra(CLICKED_MOVIE, clickedMovie);
        startActivity(detailIntent);
        Log.i(TAG, "Starting DetailActivity...");
    }


    @Override
    public void onMovieRecieved(Movie m) {
        movieList.add(m);
        adapter.notifyDataSetChanged();
    }
}
