package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.mollie.GetPaymentTask;
import com.avans2018.klasd.cineapp.data_access_layer.mollie.PaymentListener;
import com.avans2018.klasd.cineapp.domain_layer.Payment;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

public class PaymentCheckerActivity extends AppCompatActivity implements PaymentListener {

    private final static String TAG = "PaymentCheckerActivity";
    final static String PROCESSED_TICKETLIST = "processedTickets";
    private Toolbar toolbar;
    private Payment payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() called.");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_checker);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(StringLimiter.limit(getResources().getString(R.string.paymentHeader), 25));

        Intent ticketData = getIntent();
        final ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) ticketData.getSerializableExtra(CheckoutActivity.PROCESSED_TICKETLIST);
        Payment receivedPayment = (Payment) ticketData.getSerializableExtra("PAYMENTDATA");
        GetPaymentTask getPaymentTask = new GetPaymentTask(this, receivedPayment);
        getPaymentTask.execute();

    }


    @Override
    public void onPaymentReceived(Payment p) {

        Intent ticketData = getIntent();
        final ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) ticketData.getSerializableExtra(PaymentActivity.PROCESSED_TICKETLIST);

        Intent paymentFailedIntent = new Intent(this, FailedPaymentActivity.class);
        Intent paymentSuccesIntent = new Intent(this, ConfirmationActivity.class);
        paymentSuccesIntent.putExtra(PROCESSED_TICKETLIST, ticketArrayList);
        switch (p.getStatus()) {
            case "cancelled":
                startActivity(paymentFailedIntent);
                break;
            case "expired":
                startActivity(paymentFailedIntent);
                break;
            case "failed":
                startActivity(paymentFailedIntent);
                break;
            case "paid":
                startActivity(paymentSuccesIntent);
                break;
            default:
                startActivity(paymentFailedIntent);
                break;
        }
    }
}
