package com.avans2018.klasd.cineapp.domain_layer;

import java.io.Serializable;

/**
 * Created by Tom on 28-3-2018.
 * Class zorgt voor MyTicketsActivity. Gemaakt als temp. lite-'kloon' van Ticket class, maar dan Strings ipv classes als attributen om gegevens uit database om te zetten naar items in MyTicketsListAdapter.
 */

public class TicketPrint implements Serializable{
    private int id;
    private String date;
    private String time;
    private String theater;
    private String seat;
    private String movie;
    private String paymentCategory;

    public TicketPrint(int id, String date, String time, String theater, String seat, String movie, String paymentCategory) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.theater = theater;
        this.seat = seat;
        this.movie = movie;
        this.paymentCategory = paymentCategory;
    }

    public int getId() {
        return id;
    }

    public String getTheater() {
        return theater;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSeat() {
        return seat;
    }

    public String getMovie() {
        return movie;
    }

    public String getPaymentCategory() {
        return paymentCategory;
    }
}
