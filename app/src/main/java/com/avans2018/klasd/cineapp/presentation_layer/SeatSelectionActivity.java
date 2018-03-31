package com.avans2018.klasd.cineapp.presentation_layer;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.GridLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import com.avans2018.klasd.cineapp.R;
        import com.avans2018.klasd.cineapp.application_logic_layer.OnSeatSelected;
        import com.avans2018.klasd.cineapp.application_logic_layer.SeatingAdapter;
        import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
        import com.avans2018.klasd.cineapp.domain_layer.seating.CenterSeat;
        import com.avans2018.klasd.cineapp.domain_layer.seating.EdgeSeat;
        import com.avans2018.klasd.cineapp.domain_layer.seating.EmptySeat;
        import com.avans2018.klasd.cineapp.domain_layer.seating.SelectionSeat;
        import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

        import java.util.ArrayList;
        import java.util.List;

        import static com.avans2018.klasd.cineapp.presentation_layer.DetailActivity.CLICKED_SCHEDULE;
        import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_ADULT_TICKETS;
        import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_CHILD_TICKETS;
        import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_SENIOR_TICKETS;
        import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_STUDENT_TICKETS;
        import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_TICKETS;

public class SeatSelectionActivity extends AppCompatActivity implements OnSeatSelected{
    private final static String TAG = "SeatSelectionActivity";
    private final static int COLUMNS = 5;
    private TextView txtSeatSelected;
    private Button bookSeatsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        Log.i(TAG,"Started.");

        Intent scheduleAndTicketAmounts = getIntent();
        final MovieSchedule receivedMovieSchedule = (MovieSchedule) scheduleAndTicketAmounts.getSerializableExtra(CLICKED_SCHEDULE);
        final int totalTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_TICKETS);
        final int adultTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_ADULT_TICKETS);
        final int childTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_CHILD_TICKETS);
        final int studentTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_STUDENT_TICKETS);
        final int seniorTickets = (int) scheduleAndTicketAmounts.getSerializableExtra(TOTAL_SENIOR_TICKETS);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.seat_selection_title), 25));

        txtSeatSelected = (TextView) findViewById(R.id.txt_seat_selected);
        bookSeatsButton = (Button) findViewById(R.id.bookSeatsButton);
        List<SelectionSeat> items = new ArrayList<>();
        for (int i=0; i<30; i++) {

        if (i%COLUMNS==0 || i%COLUMNS==4) {
            items.add(new EdgeSeat(String.valueOf(i)));
        } else if (i%COLUMNS==1 || i%COLUMNS==3) {
            items.add(new CenterSeat(String.valueOf(i)));
        } else {
            items.add(new EmptySeat(String.valueOf(i)));
        }
    }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.seatRecyclerView);
        recyclerView.setLayoutManager(manager);

        SeatingAdapter adapter = new SeatingAdapter(this, items);
        recyclerView.setAdapter(adapter);

        bookSeatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code voor Intent doorgeven aan ConfirmationActivity
            }
        });

    }

    @Override
    public void onSeatSelected(int count) {
        txtSeatSelected.setText(count + " seats selected");
    }
}
