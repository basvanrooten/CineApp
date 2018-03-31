package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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
        checkoutOverviewContentOne.setText("Adult tickets: " + adultTickets);

        TextView checkoutOverviewContentTwo = (TextView) findViewById(R.id.CheckoutInfoContent2);
        checkoutOverviewContentTwo.setText("Child tickets: " + childTickets);

        TextView checkoutOverviewContentThree = (TextView) findViewById(R.id.CheckoutInfoContent3);
        checkoutOverviewContentThree.setText("Student tickets: " + studentTickets);

        TextView checkoutOverviewContentFour = (TextView) findViewById(R.id.CheckoutInfoContent4);
        checkoutOverviewContentFour.setText("Senior tickets: " + seniorTickets);

        TextView checkoutOverviewContentFive = (TextView) findViewById(R.id.CheckoutInfoContent5);
        checkoutOverviewContentFive.setText("Total tickets: " + totalTickets);

        TextView checkoutOverviewContentSix = (TextView) findViewById(R.id.CheckoutInfoContent6);
        checkoutOverviewContentSix.setText("Amount to be paid: " + totalPayment);

        Button checkoutPaymentButton = (Button) findViewById(R.id.CheckoutPaymentButton);
        checkoutPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(view.getContext(), ConfirmationActivity.class);
                ArrayList<Ticket> tickets = new ArrayList<>();
                Random random = new Random();

                // (int id, String date, String time, Theater theater, Seat seat, Movie movie, PaymentCategory paymentCategory) {
                for(int i = 0; i < adultTickets; i++){
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(random.nextInt(100000000),"05-03-2018",receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new AdultPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < childTickets; i++){
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(random.nextInt(100000000),"05-03-2018",receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new ChildPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < studentTickets; i++){
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(random.nextInt(100000000),"05-03-2018",receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new StudentPayment());
                    tickets.add(ticket);
                }

                for(int i = 0; i < seniorTickets; i++){
                    Seat seat = new Seat();
                    Ticket ticket = new Ticket(random.nextInt(100000000),"05-03-2018",receivedMovieSchedule.getStartTime(),receivedMovieSchedule.getTheater(),seat,receivedMovieSchedule.getMovie(),new SeniorPayment());
                    tickets.add(ticket);
                }

                paymentIntent.putExtra(PROCESSED_TICKETLIST, tickets);

                startActivity(paymentIntent);
                Log.i(TAG, "Starting ConfirmationActivity...");
            }
        });
    }
}
