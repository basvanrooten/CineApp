package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.payment_category.PaymentCategory;

import java.io.Serializable;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Tom on 28-03-18. String date en time toegevoegd voor opslaan datum en tijd in Ticket + aanpassing constructor/getters/setters
 */

public class Ticket implements Serializable{

    private int id;
    private String date;
    private String time;
    private Theater theater;
    private Seat seat;
    private Movie movie;
    private PaymentCategory paymentCategory;

    private static final String TAG = "Domain: Ticket";

    public Ticket(int id, String date, String time, Theater theater, Seat seat, Movie movie, PaymentCategory paymentCategory) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.theater = theater;
        this.seat = seat;
        this.movie = movie;
        this.paymentCategory = paymentCategory;

        Log.d(TAG, "Ticket-Constructor (extended) called");
    }


    public Ticket() {
        Log.d(TAG, "Ticket-Constructor (empty) was called");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getDate() { return this.date; }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }
}
