package com.avans2018.klasd.cineapp.domain_layer.seating;

import com.avans2018.klasd.cineapp.domain_layer.Seat;

/**
 * Created by Tom on 31-3-2018.
 */

public abstract class SelectionSeat {
    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;

    private String label;
    private Seat seat;

    public SelectionSeat(String label, Seat seat) {
        this.seat = seat;
        this.label = label;
    }

    public Seat getSeat(){ return seat; }

    public String getLabel() {
        return label;
    }

    abstract public int getType();

}
