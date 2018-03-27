package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

import com.avans2018.klasd.cineapp.domain.PaymentMethod.PaymentMethod;

import java.util.ArrayList;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Robin on 26-03-18.
 */

public class Reservation {

    private int id;
    private ArrayList<Ticket> tickets;
    private MovieSchedule movieSchedule;
    private User user;
    private PaymentMethod paymentMethod;

    private final static String TAG = "Reservation";

    public Reservation(int id, ArrayList<Ticket> tickets, MovieSchedule movieSchedule, User user, PaymentMethod paymentMethod) {

        Log.d(TAG, "Reservation-Constructor (extended) was called");
        this.id = id;
        this.tickets = tickets;
        this.movieSchedule = movieSchedule;
        this.user = user;
        this.paymentMethod = paymentMethod;
    }

    public Reservation() {
        Log.d(TAG, "Reservation-Constructor (empty) was called");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket)    {
        this.tickets.add(ticket);
    }

    public MovieSchedule getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(MovieSchedule movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
