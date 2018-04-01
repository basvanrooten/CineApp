package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class MovieSchedule implements Serializable{

    private int id;
    private Date time;
    private Date endTime;
    private int takenPerc;
    private Movie movie;
    private Theater theater;

    private static final String TAG = "Domain: MovieSchedule";

    public MovieSchedule(int id, Date time, Movie movie, Theater theater, Date endTime, int takenPerc) {

        Log.d(TAG, "MovieSchedule-Constructor (extended) called");
        this.id = id;
        this.time = time;
        this.movie = movie;
        this.theater = theater;
        this.endTime = endTime;
        this.takenPerc = takenPerc;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    public String getStartTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.time);
    }

    public Date getDate(){
        return this.time;
    }

    public String getEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.endTime);
    }

    public int getTakenPerc() {
        return takenPerc;
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
