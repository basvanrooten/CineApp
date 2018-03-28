package com.avans2018.klasd.cineapp.domain_layer.payment_category;

import android.util.Log;

/**
 * Created by HeyRobin on 26-3-2018.
 *  * Last Edited by Robin on 26-03-18.
 */

public class PaymentCategoryFactory {
    private static final String TAG = "PaymentCategoryFactory";

    public PaymentCategory createPaymentCategory(String paymentCategory)   {
        Log.d(TAG, "createPaymentCategory called");

        if (paymentCategory == null || paymentCategory.equals(""))  {
            Log.d(TAG, "Received nothing from the parameter");
            throw new IllegalArgumentException("No valid PaymentCategory given");

        } else if (paymentCategory.equalsIgnoreCase("AdultPayment")) {
            Log.d(TAG, "Received: " + paymentCategory);
            return new AdultPayment();

        } else if (paymentCategory.equalsIgnoreCase("ChildPayment"))  {
            Log.d(TAG, "Received: " + paymentCategory);
            return new ChildPayment();

        } else if (paymentCategory.equalsIgnoreCase("StudentPayment"))    {
            Log.d(TAG, "Received: " + paymentCategory);
            return new StudentPayment();

        } else if (paymentCategory.equalsIgnoreCase("SeniorPayment")) {
            Log.d(TAG, "Received: " + paymentCategory);
            return new SeniorPayment();

        }

        Log.d(TAG, "Received nothing of value, returning null");
        return null;
    }

}
