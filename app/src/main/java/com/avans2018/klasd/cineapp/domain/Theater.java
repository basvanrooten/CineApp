package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Robin on 26-03-2018.
 */

public class Theater {

    private int theatherNumber;
    private int numberOfSeats;

    private static final String TAG = "Domain: Theater";

    public Theater(int theatherNumber, int numberOfSeats)  {
        this.numberOfSeats = numberOfSeats;
        this.theatherNumber = theatherNumber;

        Log.d(TAG, "Theater-Constructor was called. TheatherNumber: " +
                theatherNumber + ", amount of seats: " + numberOfSeats);
    }

    public Theater()   {
        Log.d(TAG, "Theater-Constructor was called. Received nothing");
    }

    public int getTheatherNumber() {
        return theatherNumber;
    }

    public void setTheatherNumber(int theatherNumber) {
        this.theatherNumber = theatherNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String toString()    {
        return "This is theather '" + this.theatherNumber +
                "', with " + this.numberOfSeats + "' number of seats";
    }
}
