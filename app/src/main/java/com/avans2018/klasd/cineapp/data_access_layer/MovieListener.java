package com.avans2018.klasd.cineapp.data_access_layer;

import com.avans2018.klasd.cineapp.domain.Movie;

/**
 * Created by Gebruiker on 27-3-2018.
 */

public interface MovieListener {

    void onMovieRecieved(Movie m);

}
