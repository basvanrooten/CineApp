package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.data_access_layer.seat.UpdateSeatStatusListener;
import com.avans2018.klasd.cineapp.data_access_layer.seat.UpdateSeatStatusTask;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.SeatStatus;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

// Class waar klant terecht komt na geslaagde aankoop. Deze class schrijft ook de ticket weg naar lokale DB.

public class ConfirmationActivity extends AppCompatActivity {
    private final static String TAG = "ConfirmationActivity";
    final static String DATABASE = "database";
    private TicketStorageDB storageDB = new TicketStorageDB(this);
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.confirmationHeader), 25));

        Intent intent = getIntent();
        ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) intent.getSerializableExtra(CheckoutActivity.PROCESSED_TICKETLIST);
        for(Ticket ticket : ticketArrayList){
            storageDB.addTicket(ticket);
            new UpdateSeatStatusTask(new UpdateSeatStatusListener() {
                @Override
                public void onSeatRecieved(Seat seat) {
                    //TODO: Nothing.
                }
            }, ticket.getMovieSchedule(), ticket.getSeat(), SeatStatus.IS_TAKEN).execute();
        }
        Toast.makeText(this, R.string.confirmation_tickets_added, Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ConfirmationActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_my_tickets) {
            Intent intent = new Intent(this,MyTicketsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
