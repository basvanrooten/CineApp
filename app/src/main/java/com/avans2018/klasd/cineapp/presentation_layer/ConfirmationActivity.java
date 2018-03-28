package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;

// Class waar klant terecht komt na geslaagde aankoop. Deze class schrijft ook de ticket weg naar lokale DB.

public class ConfirmationActivity extends AppCompatActivity {
    private final static String TAG = "ConfirmationActivity";
    private TicketStorageDB storageDB = new TicketStorageDB(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Intent intent = getIntent();
        Ticket ticket = (Ticket) intent.getSerializableExtra(CheckoutActivity.PENDING_PAYMENT);
        storageDB.addTicket(ticket);

        Log.i(TAG,"Purchase complete. Ticket added to local storage.");
    }
}
