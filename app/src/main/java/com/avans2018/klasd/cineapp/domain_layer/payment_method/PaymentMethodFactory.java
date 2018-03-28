package com.avans2018.klasd.cineapp.domain_layer.payment_method;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Robin on 26-03-18.
 */

public class PaymentMethodFactory {

    private static final String TAG = "PaymentMethodFactory";

    public PaymentMethod createPaymentMethod(String paymentMethod)  {
        Log.d(TAG, "createPaymentMethod called");

        if (paymentMethod == null || paymentMethod.equals(""))  {
            Log.d(TAG, "Received nothing from the parameter");
            throw new IllegalArgumentException("No valid PaymentMethod given");

        } else if (paymentMethod.equalsIgnoreCase("Crypto")) {
            Log.d(TAG, "Received " + paymentMethod);
            return new Crypto();

        } else if (paymentMethod.equalsIgnoreCase("CreditCard"))    {
            Log.d(TAG, "Received " + paymentMethod);
            return new CreditCard();

        } else if (paymentMethod.equalsIgnoreCase("DebitCard")) {
            Log.d(TAG, "Received " + paymentMethod);
            return new DebitCard();

        }

        Log.d(TAG, "Received nothing of value from the parameter. Returning null");
        return null;
    }
}
