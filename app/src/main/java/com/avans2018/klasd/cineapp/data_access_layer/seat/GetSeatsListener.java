package com.avans2018.klasd.cineapp.data_access_layer.seat;

import com.avans2018.klasd.cineapp.domain_layer.Seat;

import java.util.ArrayList;

/**
 * Created by Jorrit on 31-3-2018.
 */

public interface GetSeatsListener {

    void onSeatsRecieved(ArrayList<Seat> seats);
}
