package com.avans2018.klasd.cineapp.domain_layer;

import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.payment_category.PaymentCategory;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Tom on 28-03-18. String date en time toegevoegd voor opslaan datum en tijd in Ticket + aanpassing constructor/getters/setters
 */

public class Ticket implements Serializable{

    private int id;
    private Seat seat;
    private PaymentCategory paymentCategory;
    private MovieSchedule movieSchedule;

    private static final String TAG = "Domain: Ticket";

    public Ticket(int id, MovieSchedule movieSchedule, Seat seat, PaymentCategory paymentCategory) {
        this.id = id;
        this.seat = seat;
        this.movieSchedule = movieSchedule;
        this.paymentCategory = paymentCategory;

        Log.d(TAG, "Ticket-Constructor (extended) called");
    }

    public Ticket(MovieSchedule movieSchedule, Seat seat, PaymentCategory paymentCategory) {
        this.id = 0;
        this.seat = seat;
        this.movieSchedule = movieSchedule;
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
        return movieSchedule.getTheater();
    }

    public Seat getSeat() {
        return this.seat;
    }

    public String getTime() {
        return movieSchedule.getStartTime();
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Movie getMovie() {
        return movieSchedule.getMovie();
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-YY");
        String dateAsString = df.format(movieSchedule.getDate());
        return dateAsString;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }

    public MovieSchedule getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(MovieSchedule movieSchedule) {
        this.movieSchedule = movieSchedule;
    }
}
