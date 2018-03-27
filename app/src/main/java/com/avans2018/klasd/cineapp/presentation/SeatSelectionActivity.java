package com.avans2018.klasd.cineapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;

public class SeatSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        // TextView
        TextView seatSelectionPageHeader = (TextView) findViewById(R.id.seatSelectionTitle);

        // Hier nog alle losse stoelen toevoegen

    }
}
