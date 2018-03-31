package com.avans2018.klasd.cineapp.domain_layer.seating;

/**
 * Created by Tom on 31-3-2018.
 */

public class EmptySeat extends SelectionSeat{


    public EmptySeat(String label) {
        super(label);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
