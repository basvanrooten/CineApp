package com.avans2018.klasd.cineapp.domain_layer.payment_category;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Tom on 28-03-18. String CustomerType toegevoegd voor opslaan gegevens in locale DB.
 */

public class AdultPayment implements PaymentCategory {
    private final String CUSTOMER_TYPE = "Adult";

    private final double PRICE = 8.00;
    private static final String TAG = "AdultPayment";

    @Override
    public double getPrice() {
        Log.d(TAG, "Returned the value of AdultPayment: " + PRICE);
        return PRICE;
    }

    @Override
    public String getPaymentMethodString() {
        return this.CUSTOMER_TYPE;
    }
}
