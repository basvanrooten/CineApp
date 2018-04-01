package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.Theater;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.domain_layer.payment_category.AdultPayment;
import com.avans2018.klasd.cineapp.domain_layer.payment_category.ChildPayment;
import com.avans2018.klasd.cineapp.domain_layer.payment_category.SeniorPayment;
import com.avans2018.klasd.cineapp.domain_layer.payment_category.StudentPayment;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import static com.avans2018.klasd.cineapp.presentation_layer.DetailActivity.CLICKED_SCHEDULE;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_ADULT_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_AMOUNT;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_CHILD_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_SENIOR_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_STUDENT_TICKETS;
import static com.avans2018.klasd.cineapp.presentation_layer.TicketSelectionActivity.TOTAL_TICKETS;

public class CheckoutActivity extends AppCompatActivity{
    final static String PENDING_PAYMENT = "payment";
    final static String TAG = "CheckoutActivity";
    final static String PROCESSED_TICKETLIST = "processedTickets";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent scheduleSeatsTicketAmounts = getIntent();
        final MovieSchedule receivedMovieSchedule = (MovieSchedule) scheduleSeatsTicketAmounts.getSerializableExtra(CLICKED_SCHEDULE);
        final int totalTickets = (int) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_TICKETS);
        final int adultTickets = (int) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_ADULT_TICKETS);
        final int childTickets = (int) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_CHILD_TICKETS);
        final int studentTickets = (int) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_STUDENT_TICKETS);
        final int seniorTickets = (int) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_SENIOR_TICKETS);
        final double totalPayment = (double) scheduleSeatsTicketAmounts.getSerializableExtra(TOTAL_AMOUNT);

        // Hoofdtitel veranderen
        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.checkout_title), 25));


        // TextViews & Button
        TextView checkoutOverviewHeader = (TextView) findViewById(R.id.CheckoutInfoHeader);

        TextView checkoutOverviewContentOne = (TextView) findViewById(R.id.CheckoutInfoContent1);
        checkoutOverviewContentOne.setText(this.getString(R.string.checkout_adult_tickets) + adultTickets);

        TextView checkoutOverviewContentTwo = (TextView) findViewById(R.id.CheckoutInfoContent2);
        checkoutOverviewContentTwo.setText(this.getString(R.string.checkout_child_tickets) + childTickets);

        TextView checkoutOverviewContentThree = (TextView) findViewById(R.id.CheckoutInfoContent3);
        checkoutOverviewContentThree.setText(this.getString(R.string.checkout_student_tickets) + studentTickets);

        TextView checkoutOverviewContentFour = (TextView) findViewById(R.id.CheckoutInfoContent4);
        checkoutOverviewContentFour.setText(this.getString(R.string.checkout_senior_tickets) + seniorTickets);

        TextView checkoutOverviewContentFive = (TextView) findViewById(R.id.CheckoutInfoContent5);
        checkoutOverviewContentFive.setText(this.getString(R.string.checkout_total_tickets) + totalTickets);

        TextView checkoutOverviewContentSix = (TextView) findViewById(R.id.CheckoutInfoContent6);
        String firstPart = this.getString(R.string.checkout_payment_amount);
        double roundedNumber = round(totalPayment, 2);
        checkoutOverviewContentSix.setText(firstPart + roundedNumber);

        Button checkoutPaymentButton = (Button) findViewById(R.id.CheckoutPaymentButton);
        checkoutPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(view.getContext(), ConfirmationActivity.class);
                ArrayList<Ticket> tickets = new ArrayList<>();
                Random random = new Random();
                DateFormat df = new SimpleDateFormat("dd-MM-YY");
                String dateAsString = df.format(receivedMovieSchedule.getDate());

                // (String date, String time, Theater theater, Seat seat, Movie movie, PaymentCategory paymentCategory)
                for(int i = 0; i < adultTickets; i++){
                    // Logica van stoelnummer nog toevoegen
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(dateAsString,receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new AdultPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < childTickets; i++){
                    // Logica van stoelnummer nog toevoegen
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(dateAsString,receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new ChildPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < studentTickets; i++){
                    // Logica van stoelnummer nog toevoegen
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(dateAsString,receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new StudentPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < seniorTickets; i++){
                    // Logica van stoelnummer nog toevoegen
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(dateAsString,receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new SeniorPayment());
                    tickets.add(ticket);
                }

                paymentIntent.putExtra(PROCESSED_TICKETLIST, tickets);

                startActivity(paymentIntent);
                Log.i(TAG, "Starting ConfirmationActivity...");
            }
        });
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
