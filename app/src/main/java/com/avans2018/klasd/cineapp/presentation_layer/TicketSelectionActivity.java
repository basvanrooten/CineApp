package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

public class TicketSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_selection);

        // Intent met data van MovieSchedule vanuit DetailActivity
        Intent intent = getIntent();
        MovieSchedule clickedSchedule = (MovieSchedule) intent.getSerializableExtra(DetailActivity.CLICKED_SCHEDULE);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.my_ticket_detail_title), 25));

        // TextViews
        TextView ticketInfoHeader = (TextView) findViewById(R.id.ticketSelectionInfoHeader);
        TextView ticketMovie = (TextView) findViewById(R.id.ticketSelectionInfoMovie);
        TextView ticketDate = (TextView) findViewById(R.id.ticketSelectionInfoDate);
        TextView ticketStartTime = (TextView) findViewById(R.id.ticketSelectionInfoStartTime);
        TextView ticketEndTime = (TextView) findViewById(R.id.ticketSelectionInfoEndTime);
        TextView selectorHeader = (TextView) findViewById(R.id.ticketSelectionSelectorHeader);

        TextView selectorAdult = (TextView) findViewById(R.id.ticketSelectorTextAdult);
        TextView selectorAdultInput = (TextView) findViewById(R.id.ticketSelectorInputAdult);

        TextView selectorChild = (TextView) findViewById(R.id.ticketSelectorTextChild);
        TextView selectorChildInput = (TextView) findViewById(R.id.ticketSelectorInputChild);

        TextView selectorStudent = (TextView) findViewById(R.id.ticketSelectorTextStudent);
        TextView selectorStudentInput = (TextView) findViewById(R.id.ticketSelectorInputStudent);

        TextView selectorSenior = (TextView) findViewById(R.id.ticketSelectorTextSenior);
        TextView selectorSeniorInput = (TextView) findViewById(R.id.ticketSelectorInputSenior);

        // Buttons
        Button continueToSeatSelectionButton = (Button) findViewById(R.id.ticketSelectionContinueToSeatSelectionButton);



    }
}
