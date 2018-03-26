package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class MovieSchedual {

    private int id;
    private int time;
    private Movie movie;
    private Theather theather;

    private static final String TAG = "Domain: MovieSchedual";

    public MovieSchedual()  {
        Log.d(TAG, "MovieSchedual-Constructor called");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theather getTheather() {
        return theather;
    }

    public void setTheather(Theather theather) {
        this.theather = theather;
    }

    @Override
    public String toString() {
        return "MovieSchedual{" +
                "movie=" + movie.toString() +
                ", time=" + time +
                ", theather=" + theather.getTheatherNumber() +
                '}';
    }
}
