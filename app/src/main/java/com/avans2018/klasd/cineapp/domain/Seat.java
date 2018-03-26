package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class Seat {

    private int seatNumber;
    private Theather theather;
    private boolean taken;

    private final static String TAG = "Domain: Seat";

    public Seat(int seatNumber, Theather theather, boolean taken) {

        Log.d(TAG, "Seat-Constructor (extended) was called");
        this.seatNumber = seatNumber;
        this.theather = theather;
        this.taken = taken;
    }

    public Seat() {
        Log.d(TAG, "Seat-Constructor (empty) was called");
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Theather getTheather() {
        return theather;
    }

    public void setTheather(Theather theather) {
        this.theather = theather;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public static String getTAG() {
        return TAG;
    }
}
