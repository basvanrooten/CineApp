package com.avans2018.klasd.cineapp.domain.PaymentMethod;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 */

public class DebitCard implements PaymentMethod {

    private static final String TAG = "DebitCard";

    @Override
    public boolean withdrawalAmount(int amount) {
        Log.d(TAG, "withdrawalAmount called. Reduced balance with " + amount + ".");
        return true;
    }
}
