package com.avans2018.klasd.cineapp.presentation_layer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.avans2018.klasd.cineapp.R;
import com.avans2018.klasd.cineapp.data_access_layer.mollie.CreatePaymentTask;
import com.avans2018.klasd.cineapp.data_access_layer.mollie.PaymentListener;
import com.avans2018.klasd.cineapp.domain_layer.Payment;
import com.avans2018.klasd.cineapp.domain_layer.Ticket;
import com.avans2018.klasd.cineapp.util_layer.StringLimiter;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity implements PaymentListener{
    private final static String TAG = "PaymentActivity";
    final static String PROCESSED_TICKETLIST = "processedTickets";
    private Toolbar toolbar;
    private Payment payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Betaling");

        Intent paymentData = getIntent();
        Payment receivedPayment = (Payment) paymentData.getSerializableExtra("PAYMENTDATA");

        CreatePaymentTask createPaymentTask = new CreatePaymentTask(this, receivedPayment);
        createPaymentTask.execute();
        final ArrayList<Ticket> ticketArrayList = (ArrayList<Ticket>) paymentData.getSerializableExtra(CheckoutActivity.PROCESSED_TICKETLIST);
        WebView webView = (WebView) findViewById(R.id.WebViewPayment);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.equals(payment.getRedirectUrl())) {
                    //Do your thing
                    Intent paymentIntent = new Intent(view.getContext(), ConfirmationActivity.class);
                    paymentIntent.putExtra(PROCESSED_TICKETLIST, ticketArrayList);
                    startActivity(paymentIntent);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    @Override
    public void onPaymentReceived(Payment p) {
        this.payment = p;
        WebView webView = (WebView) findViewById(R.id.WebViewPayment);

        webView.loadUrl(p.getPaymentUrl());
    }
}
