package com.avans2018.klasd.cineapp.data_access_layer.movie;

import com.avans2018.klasd.cineapp.domain_layer.Movie;

/**
 * Created by Gebruiker on 27-3-2018.
 */

public interface MovieListener {

    void onMovieRecieved(Movie m);

}
