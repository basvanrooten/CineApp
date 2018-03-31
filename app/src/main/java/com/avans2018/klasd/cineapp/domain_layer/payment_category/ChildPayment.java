package com.avans2018.klasd.cineapp.domain_layer.payment_category;

import android.util.Log;

import java.io.Serializable;

import static com.avans2018.klasd.cineapp.application_logic_layer.TicketPrices.childTicketPrice;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Tom on 28-03-18. String CustomerType toegevoegd voor opslaan gegevens in locale DB.
 */

public class ChildPayment implements PaymentCategory, Serializable {
    private final String CUSTOMER_TYPE = "Child";
    private final double PRICE = childTicketPrice;
    private static final String TAG = "ChildPayment";


    @Override
    public double getPrice() {
        Log.d(TAG, "Returned the value of ChildPayment: " + PRICE);
        return PRICE;
    }

    @Override
    public String getPaymentMethodString() {
        return this.CUSTOMER_TYPE;
    }


}
