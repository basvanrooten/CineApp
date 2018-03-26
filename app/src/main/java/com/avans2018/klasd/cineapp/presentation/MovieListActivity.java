package com.avans2018.klasd.cineapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic.MovieListAdapter;
import com.avans2018.klasd.cineapp.application_logic.OnItemClickListener;
import com.avans2018.klasd.cineapp.domain.Movie;

import java.util.ArrayList;

// Lijst van films waar je komt na login
public class MovieListActivity extends AppCompatActivity implements OnItemClickListener{
    private final static String TAG = "MovieListActivity";

    private RecyclerView recyclerView;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MovieListAdapter adapter = new MovieListAdapter(MovieListActivity.this,movieList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MovieListActivity.this);

    }

    @Override
    public void onItemClick(int position) {

    }
}
