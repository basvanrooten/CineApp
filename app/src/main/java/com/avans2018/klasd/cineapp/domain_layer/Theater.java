package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Wouter Kodde on 26-03-2018.
 */

public class Theater implements Serializable{

    private int theaterNumber;
    private String theaterName;

    private static final String TAG = "Domain: Theater";

    public Theater(int theaterNumber, String theaterName)  {
        this.theaterNumber = theaterNumber;
        this.theaterName = theaterName;

        Log.d(TAG, "Theater-Constructor was called. TheaterNumber: " +
                theaterNumber + ", with the name: " + theaterName);
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

    public Theater(String theaterName) {
        this.theaterName = theaterName;
    }

    public String toString()    {
        return "This is theater '" + this.theaterNumber +
                "', with the name " + this.theaterName;
    }
}
