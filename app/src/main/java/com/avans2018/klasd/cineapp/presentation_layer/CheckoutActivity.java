package com.avans2018.klasd.cineapp.presentation_layer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.avans2018.klasd.cineapp.R;

public class CheckoutActivity extends AppCompatActivity{
    final static String PENDING_PAYMENT = "payment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // TextViews & Button
        TextView checkoutPageHeader = (TextView) findViewById(R.id.checkoutTitle);

        TextView checkoutOverviewHeader = (TextView) findViewById(R.id.CheckoutInfoHeader);

        TextView checkoutOverviewContent = (TextView) findViewById(R.id.CheckoutInfoContent);

        Button checkoutPaymentButton = (Button) findViewById(R.id.CheckoutPaymentButton);
        // XML nog aanpassen met meerdere velden. Code schrijven voor het vullen van deze velden

    }
}
