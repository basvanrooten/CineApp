package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class MovieSchedule {

    private int id;
    private int time;
    private Movie movie;
    private Theater theater;

    private static final String TAG = "Domain: MovieSchedule";

    public MovieSchedule(int id, int time, Movie movie, Theater theater) {

        Log.d(TAG, "MovieSchedule-Constructor (extended) called");
        this.id = id;
        this.time = time;
        this.movie = movie;
        this.theater = theater;
    }

    public MovieSchedule()  {
        Log.d(TAG, "MovieSchedule-Constructor (empty) called");
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

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    @Override
    public String toString() {
        return "MovieSchedule{" +
                "movie=" + movie.toString() +
                ", time=" + time +
                ", theater=" + theater.getTheaterNumber() +
                '}';
    }
}
