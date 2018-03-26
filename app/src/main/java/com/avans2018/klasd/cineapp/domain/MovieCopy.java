package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class MovieCopy {

    private Movie movie;
    private final static String TAG = "Domain: MovieCopy";

    public MovieCopy(Movie movie)   {
        this.movie = movie;

        Log.d(TAG, "MovieCopy-Constructor was called. "
                + "Received: " + movie.toString());
    }

    public MovieCopy()  {
        Log.d(TAG, "MovieCopy-Constructor (empty) was called.");
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String toString()    {
        return "This is a copy of the '" + movie.toString() + "' movie";
    }
}
