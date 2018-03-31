package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

// Class waar klant terecht komt na geslaagde aankoop. Deze class schrijft ook de ticket weg naar lokale DB.

public class ConfirmationActivity extends AppCompatActivity {
    private final static String TAG = "ConfirmationActivity";
    final static String DATABASE = "database";
    private TicketStorageDB storageDB = new TicketStorageDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.confirmationHeader), 25));

        Intent intent = getIntent();
        ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) intent.getSerializableExtra(CheckoutActivity.PROCESSED_TICKETLIST);
        for(Ticket ticket : ticketArrayList){
            storageDB.addTicket(ticket);
        }
        Toast.makeText(this, "Tickets added to My Tickets", Toast.LENGTH_SHORT).show();
        Log.i(TAG,"Purchase complete. Tickets added to local storage.");

        Button button = findViewById(R.id.ViewTicketsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onItemClick() called.");
                Intent myTicketsIntent = new Intent(view.getContext(), MyTicketsActivity.class);
//                myTicketsIntent.putExtra(DATABASE, storageDB);
                startActivity(myTicketsIntent);
                Log.i(TAG, "Starting MyTicketsActivity...");
            }
        });
    }
}
