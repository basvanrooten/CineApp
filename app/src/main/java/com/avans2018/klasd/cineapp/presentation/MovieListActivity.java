package com.avans2018.klasd.cineapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic.OnItemClickListener;
import com.avans2018.klasd.cineapp.domain.Movie;

import java.util.ArrayList;

// Lijst van films waar je komt na login
public class MovieListActivity extends AppCompatActivity implements OnItemClickListener{
    private final static String TAG = "MovieListActivity";
    final static String CLICKED_MOVIE = "clickedMovie";

    private RecyclerView recyclerView;
    private Spinner movieFilter;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter = new MovieListAdapter(MovieListActivity.this,movieList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Spinner voor filtering op films
        // Momenteel nog nonfunctioneel ivm nog geen database om selectie op aan te passen
        movieFilter = (Spinner) findViewById(R.id.movieFilterSpinner);
        String[] filterOptions = new String[]{"Popular", "New", "Best rated"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filterOptions);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        movieFilter.setAdapter(spinnerAdapter);

        // RecyclerView voor het weergeven van lijst van films
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MovieListActivity.this);
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
}
