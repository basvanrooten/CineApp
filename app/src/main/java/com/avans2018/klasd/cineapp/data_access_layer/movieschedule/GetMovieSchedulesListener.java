package com.avans2018.klasd.cineapp.data_access_layer.movieschedule;

import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 28-3-2018.
 */

public interface GetMovieSchedulesListener {

    void onMovieSchedulesRecieved(ArrayList<MovieSchedule> movieSchedules);

}
