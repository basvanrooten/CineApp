package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

public class MyTicketsDetailActivity extends AppCompatActivity {
    private final static String TAG = "MyTicketsDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets_detail);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.my_tickets_title), 25));

        // Intent met data van film vanuit MyTicketsActivity
        Intent intent = getIntent();
        Ticket clickedTicket = (Ticket) intent.getSerializableExtra(MyTicketsActivity.CLICKED_TICKET);

    }
}
