package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.TicketStorageDB;
import com.avans2018.klasd.cineapp.data_access_layer.seat.UpdateSeatStatusListener;
import com.avans2018.klasd.cineapp.data_access_layer.seat.UpdateSeatStatusTask;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.SeatStatus;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

public class FailedPaymentActivity extends AppCompatActivity {
    private final static String TAG = "FailedPaymentActivity";
    private Toolbar toolbar;
    private TextView textHeader;
    private TextView cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_payment);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        textHeader = (TextView) findViewById(R.id.FailedText);
        cancelButton = (Button) findViewById(R.id.FailedButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FailedPaymentActivity.this, MainActivity.class));
            }
        });

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.failedHeader), 25));

        Intent intent = getIntent();
        ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) intent.getSerializableExtra(PaymentCheckerActivity.PROCESSED_TICKETLIST);
        for(Ticket ticket : ticketArrayList){
            new UpdateSeatStatusTask(new UpdateSeatStatusListener() {
                @Override
                public void onSeatRecieved(Seat seat) {
                    //TODO: Nothing.
                }
            }, ticket.getMovieSchedule(), ticket.getSeat(), SeatStatus.FREE).execute();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(FailedPaymentActivity.this, MainActivity.class));
        finish();
    }
}
