package com.avans2018.klasd.cineapp.domain_layer.payment_method;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 */

public class Crypto implements PaymentMethod {

    private static final String TAG = "Crypto";

    @Override
    public boolean withdrawalAmount(int amount) {
        Log.d(TAG, "withdrawalAmount called. Reduced balance with " + amount + ".");
        return true;
    }
}
