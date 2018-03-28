package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Robin on 26-03-2018.
 */

public class Theater {

    private int theaterNumber;
    private int numberOfSeats;

    private static final String TAG = "Domain: Theater";

    public Theater(int theaterNumber, int numberOfSeats)  {
        this.numberOfSeats = numberOfSeats;
        this.theaterNumber = theaterNumber;

        Log.d(TAG, "Theater-Constructor was called. TheaterNumber: " +
                theaterNumber + ", amount of seats: " + numberOfSeats);
    }

    public Theater()   {
        Log.d(TAG, "Theater-Constructor was called. Received nothing");
    }

    public int getTheaterNumber() {
        return theaterNumber;
    }

    public void setTheaterNumber(int theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String toString()    {
        return "This is theater '" + this.theaterNumber +
                "', with " + this.numberOfSeats + "' number of seats";
    }
}
