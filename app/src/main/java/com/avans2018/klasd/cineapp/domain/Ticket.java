package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

import com.avans2018.klasd.cineapp.domain.PaymentCategory.PaymentCategory;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class Ticket {

    private int id;
    private Theater theater;
    private Seat seat;
    private Movie movie;
    private PaymentCategory paymentCategory;

    private static final String TAG = "Domain: Ticket";

    public Ticket(int id, Theater theater, Seat seat, Movie movie, PaymentCategory paymentCategory) {
        this.id = id;
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }

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
