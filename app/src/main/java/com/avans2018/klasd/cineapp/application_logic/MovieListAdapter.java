package com.avans2018.klasd.cineapp.application_logic;

import android.content.Context;
import android.widget.AdapterView;

import com.avans2018.klasd.cineapp.domain.Movie;

import java.util.ArrayList;

/**
 * Created by Tom on 26-3-2018.
 * Adapter voor RecyclerView in MovieListActivity
 */

public class MovieListAdapter extends RecyclerView.Adapter{

    private Context mContext;
    private ArrayList<Movie> personList;
    private AdapterView.OnItemClickListener listener;

}
