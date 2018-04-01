package com.avans2018.klasd.cineapp.domain_layer.seating;

import com.avans2018.klasd.cineapp.domain_layer.Seat;

/**
 * Created by Tom on 31-3-2018.
 */

public class EmptySeat extends SelectionSeat{


    public EmptySeat(String label, Seat seat) {
        super(label, seat);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
