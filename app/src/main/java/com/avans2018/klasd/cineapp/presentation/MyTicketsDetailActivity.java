package com.avans2018.klasd.cineapp.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain.Ticket;

public class MyTicketsDetailActivity extends AppCompatActivity {
    private final static String TAG = "MyTicketsDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets_detail);

        // Intent met data van film vanuit MyTicketsActivity
        Intent intent = getIntent();
        Ticket clickedTicket = (Ticket) intent.getSerializableExtra(MyTicketsActivity.CLICKED_TICKET);

    }
}
