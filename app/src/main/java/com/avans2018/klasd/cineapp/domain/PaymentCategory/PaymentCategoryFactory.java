package com.avans2018.klasd.cineapp.domain.PaymentCategory;

/**
 * Created by HeyRobin on 26-3-2018.
 *  * Last Edited by Robin on 26-03-18.
 */

public class PaymentCategoryFactory {


    public PaymentCategory createPaymentCategory(String paymentCategory)   {
        if (paymentCategory == null || paymentCategory.equals(""))  {
            throw new IllegalArgumentException("No valid PaymentCategory given");

        } else if (paymentCategory.equalsIgnoreCase("AdultPayment")) {
            return new AdultPayment();

        } else if (paymentCategory.equalsIgnoreCase("ChildPayment"))  {
            return new ChildPayment();

        } else if (paymentCategory.equalsIgnoreCase("StudentPayment"))    {
            return new StudentPayment();

        } else if (paymentCategory.equalsIgnoreCase("SeniorPayment")) {
            return new SeniorPayment();

        }

        return null;
    }


}
