package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.adultTicketPrice;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.childTicketPrice;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.seniorTicketPrice;
import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.studentTicketPrice;
import static com.avans2018.klasd.cineapp.presentation_layer.DetailActivity.CLICKED_SCHEDULE;

public class TicketSelectionActivity extends AppCompatActivity{
    private final static String TAG = "TicketSelectionActivity";
    final static String TOTAL_TICKETS = "totalTickets";
    final static String TOTAL_ADULT_TICKETS = "totalAdultTickets";
    final static String TOTAL_CHILD_TICKETS = "totalChildTickets";
    final static String TOTAL_STUDENT_TICKETS = "totalStudentTickets";
    final static String TOTAL_SENIOR_TICKETS = "totalSeniorTickets";
    final static String TOTAL_AMOUNT = "totalAmount";
    double totalPriceAmount = 0;
    private Toolbar toolbar;
    private Context context;
    private int valueAdult = 0;
    private int valueChild = 0;
    private int valueStudent = 0;
    private int valueSenior = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_selection);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Intent met data van MovieSchedule vanuit DetailActivity
        Intent ticketSelectionReceiveIntent = getIntent();
        final MovieSchedule clickedSchedule = (MovieSchedule) ticketSelectionReceiveIntent.getSerializableExtra(CLICKED_SCHEDULE);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.my_ticket_detail_title), 25));

        // TextViews
        TextView ticketMovie = (TextView) findViewById(R.id.ticketSelectionInfoMovie);
        ticketMovie.setText(clickedSchedule.getMovie().getName());

        // MovieSchedule heeft nog geen datumveld
        TextView ticketDate = (TextView) findViewById(R.id.ticketSelectionInfoDate);
        String ticketDateText = this.getString(R.string.ticket_selection_info_date);
        ticketDate.setText(ticketDateText);

        TextView ticketStartTime = (TextView) findViewById(R.id.ticketSelectionInfoStartTime);
        String startTimeText = this.getString(R.string.ticket_selection_info_start_time);
        String startTime =  startTimeText + clickedSchedule.getStartTime();
        ticketStartTime.setText(startTime);
        TextView ticketEndTime = (TextView) findViewById(R.id.ticketSelectionInfoEndTime);

        String endTimeText = this.getString(R.string.ticket_selection_info_end_time);
        String endTime = endTimeText + clickedSchedule.getEndTime();
        ticketEndTime.setText(endTime);

        TextView selectorHeader = (TextView) findViewById(R.id.ticketSelectionSelectorHeader);

        TextView selectorAdult = (TextView) findViewById(R.id.ticketSelectorTextAdult);
        final TextView selectorAdultInput = (TextView) findViewById(R.id.ticketSelectorInputAdult);
        selectorAdultInput.setText("" + valueAdult);

        TextView selectorChild = (TextView) findViewById(R.id.ticketSelectorTextChild);
        final TextView selectorChildInput = (TextView) findViewById(R.id.ticketSelectorInputChild);
        selectorChildInput.setText("" + valueChild);

        TextView selectorStudent = (TextView) findViewById(R.id.ticketSelectorTextStudent);
        final TextView selectorStudentInput = (TextView) findViewById(R.id.ticketSelectorInputStudent);
        selectorStudentInput.setText("" + valueStudent);

        TextView selectorSenior = (TextView) findViewById(R.id.ticketSelectorTextSenior);
        final TextView selectorSeniorInput = (TextView) findViewById(R.id.ticketSelectorInputSenior);
        selectorSeniorInput.setText("" + valueSenior);



        // Buttons
        Button continueToSeatSelectionButton = (Button) findViewById(R.id.ticketSelectionContinueToSeatSelectionButton);
        continueToSeatSelectionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try {
                    int adultTicketsAmount = Integer.parseInt(selectorAdultInput.getText().toString());
                    int childTicketsAmount = Integer.parseInt(selectorChildInput.getText().toString());
                    int studentTicketsAmount = Integer.parseInt(selectorStudentInput.getText().toString());
                    int seniorTicketsAmount = Integer.parseInt(selectorSeniorInput.getText().toString());
                    int totalTicketCount = adultTicketsAmount + childTicketsAmount + studentTicketsAmount + seniorTicketsAmount;

                    totalPriceAmount = (double) 0;
                    totalPriceAmount = (double) totalPriceAmount + (adultTicketsAmount * adultTicketPrice);
                    totalPriceAmount = (double) totalPriceAmount + (childTicketsAmount * childTicketPrice);
                    totalPriceAmount = (double) totalPriceAmount + (studentTicketsAmount * studentTicketPrice);
                    totalPriceAmount = (double) totalPriceAmount + (seniorTicketsAmount * seniorTicketPrice);

                    if(totalTicketCount<=0){

                } else {
                    Intent seatSelectionIntent = new Intent(v.getContext(), SeatSelectionActivity.class);
                    MovieSchedule schedule = clickedSchedule;
                    seatSelectionIntent.putExtra(CLICKED_SCHEDULE, schedule);
                    seatSelectionIntent.putExtra(TOTAL_TICKETS,totalTicketCount);
                    seatSelectionIntent.putExtra(TOTAL_ADULT_TICKETS,adultTicketsAmount);
                    seatSelectionIntent.putExtra(TOTAL_CHILD_TICKETS,childTicketsAmount);
                    seatSelectionIntent.putExtra(TOTAL_STUDENT_TICKETS,studentTicketsAmount);
                    seatSelectionIntent.putExtra(TOTAL_SENIOR_TICKETS,seniorTicketsAmount);
                    seatSelectionIntent.putExtra(TOTAL_AMOUNT,totalPriceAmount);

                    startActivity(seatSelectionIntent);
                    Log.i(TAG, "Starting SeatSelectionActivity...");
                }
                } catch (Exception e){
                    Log.i(TAG, "Error with one of the inputfields.");
                    Toast.makeText(TicketSelectionActivity.this, "Select valid amount of tickets.", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // Increase/Decrease button logic Adult
    public void increaseIntegerAdult(View view) {
        valueAdult = valueAdult + 1;
        displayAdult(valueAdult);
    }
    public void decreaseIntegerAdult(View view) {
        if(valueAdult >= 1) {
            valueAdult = valueAdult - 1;
        }
        displayAdult(valueAdult);
    }
    private void displayAdult(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.ticketSelectorInputAdult);
        displayInteger.setText("" + number);
    }

    // Increase/Decrease button logic Child
    public void increaseIntegerChild(View view) {
        valueChild = valueChild + 1;
        displayChild(valueChild);
    }
    public void decreaseIntegerChild(View view) {
        if(valueChild >= 1){
            valueChild = valueChild - 1;
        }
        displayChild(valueChild);
    }
    private void displayChild(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.ticketSelectorInputChild);
        displayInteger.setText("" + number);
    }

    // Increase/Decrease button logic Student
    public void increaseIntegerStudent(View view) {
        valueStudent = valueStudent + 1;
        displayStudent(valueStudent);
    }
    public void decreaseIntegerStudent(View view) {
        if(valueStudent >= 1){
            valueStudent = valueStudent - 1;
        }
        displayStudent(valueStudent);
    }
    private void displayStudent(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.ticketSelectorInputStudent);
        displayInteger.setText("" + number);
    }

    // Increase/Decrease button logic Senior
    public void increaseIntegerSenior(View view) {
        valueSenior = valueSenior + 1;
        displaySenior(valueSenior);
    }
    public void decreaseIntegerSenior(View view) {
        if(valueSenior >= 1){
            valueSenior = valueSenior - 1;
        }
        displaySenior(valueSenior);
    }
    private void displaySenior(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.ticketSelectorInputSenior);
        displayInteger.setText("" + number);
    }


}
