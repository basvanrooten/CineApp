package com.avans2018.klasd.cineapp.domain.PaymentCategory;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class AdultPayment implements PaymentCategory {

    private final double PRICE = 8.00;
    private static final String TAG = "AdultPayment";

    @Override
    public double getPrice() {
        Log.d(TAG, "Returned the value of AdultPayment: " + PRICE);
        return PRICE;
    }
}