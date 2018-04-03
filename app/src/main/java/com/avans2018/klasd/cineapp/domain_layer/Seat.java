package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class Seat implements Serializable {

    private int seatId;
    private int seatNumber;
    private int rowNumber;
    private Theater theater;
    private SeatStatus status;

    private final static String TAG = "Domain: Seat";

    public Seat(int seatId,int seatNumber, int rowNumber, Theater theater, SeatStatus status) {

        Log.d(TAG, "Seat-Constructor (extended) was called");
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.theater = theater;
        this.status = status;
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

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setSeatId(int seatId) {

        this.seatId = seatId;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public boolean isTaken() {
        if(status == SeatStatus.IS_RESERVATED || status == SeatStatus.IS_TAKEN){
            return true;
        }
        return false;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public static String getTAG() {
        return TAG;
    }

    public String returnTheaterForDB() {
//        return this.theater.getTheaterName() + " : " + getSeatNumber();    // Code van Wouter
        return this.theater.getTheaterName();
    }

    public String returnTheaterNumberForDB() {
        String numberString = "" + this.getTheater().getTheaterNumber();
        return numberString;
    }

    public int returnSeatNumberForDB() {
        return this.seatNumber;
    }
}
