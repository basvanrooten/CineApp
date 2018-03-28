package com.avans2018.klasd.cineapp.presentation_layer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.OnItemClickListener;

public class SeatSelectionActivity extends AppCompatActivity implements OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);

        // TextView
        TextView seatSelectionPageHeader = (TextView) findViewById(R.id.seatSelectionTitle);

        // Hier nog alle losse stoelen toevoegen

    }

    @Override
    public void onItemClick(int position) {

    }
}
