package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic_layer.OnItemClickListener;
import com.avans2018.klasd.cineapp.data_access_layer.MovieListTask;
import com.avans2018.klasd.cineapp.data_access_layer.MovieListener;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.domain_layer.Movie;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

// Filmoverzicht - Beginscherm van de app
public class MainActivity extends AppCompatActivity implements OnItemClickListener, MovieListener {
    private final static String TAG = "MainActivity";
    final static String CLICKED_MOVIE = "clickedMovie";

    private RecyclerView recyclerView;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter = new MovieListAdapter(MainActivity.this, movieList);

    // Misschien overbodig
    private TicketStorageDB storageDB = new TicketStorageDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.movies_to_watch), 25));

        MovieListTask movieListTask = new MovieListTask(this);
        movieListTask.execute();

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
