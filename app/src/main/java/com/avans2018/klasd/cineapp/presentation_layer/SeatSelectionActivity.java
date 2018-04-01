package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.application_logic_layer.OnSeatSelected;
import com.avans2018.klasd.cineapp.application_logic_layer.SeatingAdapter;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.seating.CenterSeat;
import com.avans2018.klasd.cineapp.domain_layer.seating.EdgeSeat;
import com.avans2018.klasd.cineapp.domain_layer.seating.EmptySeat;
import com.avans2018.klasd.cineapp.domain_layer.seating.SelectionSeat;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;
import java.util.List;

import static com.avans2018.klasd.cineapp.presentation_layer.DetailActivity.CLICKED_SCHEDULE;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.SEAT_LIST;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_ADULT_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_AMOUNT;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_CHILD_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_SENIOR_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_STUDENT_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_TICKETS;

public class SeatSelectionActivity extends AppCompatActivity implements OnSeatSelected{
    private final static String TAG = "SeatSelectionActivity";
    private final static int COLUMNS = 9;
    private TextView txtSeatSelected;
    private TextView totalSeatsText;
    private Button bookSeatsButton;
    private ArrayList<String> selectedSeats = new ArrayList<>();
    private int selectedSeatsCount = 0;
    private Toolbar toolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        Log.i(TAG,"Started.");

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent scheduleAndTicketAmounts = getIntent();
        final MovieSchedule receivedMovieSchedule = (MovieSchedule) scheduleAndTicketAmounts.getSerializableExtra(CLICKED_SCHEDULE);
        final int totalTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_TICKETS);
        final int adultTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_ADULT_TICKETS);
        final int childTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_CHILD_TICKETS);
        final int studentTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_STUDENT_TICKETS);
        final int seniorTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_SENIOR_TICKETS);
        final double totalPaymentAmount = (double) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_AMOUNT);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.seat_selection_title), 25));

        totalSeatsText = (TextView) findViewById(R.id.totalSeats);
        String totalSeatsTextBeginPart = this.getString(R.string.select_seats_start);
        String totalSeatsTextEndPart = this.getString(R.string.select_seats_end);
        String totalSeatsTextString = totalSeatsTextBeginPart + totalTickets + totalSeatsTextEndPart;
        totalSeatsText.setText(totalSeatsTextString);
        txtSeatSelected = (TextView) findViewById(R.id.txt_seat_selected);
        bookSeatsButton = (Button) findViewById(R.id.bookSeatsButton);
        final List<SelectionSeat> items = new ArrayList<>();
        Seat seat;
        for (int i=0; i<72; i++) {
        seat = receivedMovieSchedule.getTheater().getSeats().get(i);
        if (i%COLUMNS==0 || i%COLUMNS==9) {
            items.add(new EdgeSeat(String.valueOf(i), seat));
        } else if (i%COLUMNS==1 || i%COLUMNS==2 || i%COLUMNS==3 || i%COLUMNS==4 || i%COLUMNS==5 || i%COLUMNS==6 || i%COLUMNS==7 || i%COLUMNS==8) {
            items.add(new CenterSeat(String.valueOf(i), seat));
        } else {
            items.add(new EmptySeat(String.valueOf(i), seat));
        }
    }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.seatRecyclerView);
        recyclerView.setLayoutManager(manager);

        final SeatingAdapter adapter = new SeatingAdapter(this, items);
        recyclerView.setAdapter(adapter);

        bookSeatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code voor Intent doorgeven stoelselectie aan ConfirmationActivity
                // nog schrijven
                //
                if (selectedSeatsCount == totalTickets) {
                    Intent confirmationIntent = new Intent(view.getContext(), CheckoutActivity.class);

                    ArrayList<Seat> seats = new ArrayList<>();

                    for (Integer i : adapter.getSelectedItems()) {
                        seats.add(items.get(i).getSeat());
                    }


                    MovieSchedule schedule = receivedMovieSchedule;
                    confirmationIntent.putExtra(CLICKED_SCHEDULE, schedule);
                    confirmationIntent.putExtra(TOTAL_TICKETS, totalTickets);
                    confirmationIntent.putExtra(TOTAL_ADULT_TICKETS, adultTickets);
                    confirmationIntent.putExtra(TOTAL_CHILD_TICKETS, childTickets);
                    confirmationIntent.putExtra(TOTAL_STUDENT_TICKETS, studentTickets);
                    confirmationIntent.putExtra(TOTAL_SENIOR_TICKETS, seniorTickets);
                    confirmationIntent.putExtra(TOTAL_AMOUNT, totalPaymentAmount);
                    confirmationIntent.putExtra(SEAT_LIST, seats);

                    startActivity(confirmationIntent);
                    Log.i(TAG, "Starting CheckoutActivity...");
                } else {
                    Toast.makeText(SeatSelectionActivity.this, R.string.seat_selection_select_correct_amount, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Incorrect amount of seats selected.");
                }
            }
        });




    }

    @Override
    public void onSeatSelected(int count) {
        String textEndPart = " " + this.getString(R.string.amount_selected);
        String text = "" + count + textEndPart;
        txtSeatSelected.setText(text);
        selectedSeatsCount = count;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
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
