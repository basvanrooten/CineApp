package com.avans2018.klasd.cineapp.domain_layer.seating;

/**
 * Created by Tom on 31-3-2018.
 */

public abstract class SelectionSeat {
    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;

    private String label;


    public SelectionSeat(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }

    abstract public int getType();

}
