package com.avans2018.klasd.cineapp.domain_layer.payment_category;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Tom on 28-03-18. String CustomerType toegevoegd voor opslaan gegevens in locale DB.
 */

public class ChildPayment implements PaymentCategory {
    private final String CUSTOMER_TYPE = "Child";
    private final double PRICE = 6.00;
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
