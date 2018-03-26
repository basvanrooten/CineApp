package com.avans2018.klasd.cineapp.domain.PaymentMethod;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last edited by Robin on 26-03-18.
 */

public class PaymentMethodFactory {

    public PaymentMethod createPaymentMethod(String paymentMethod)  {
        if (paymentMethod == null || paymentMethod.equals(""))  {
            throw new IllegalArgumentException("No valid PaymentMethod given");

        } else if (paymentMethod.equalsIgnoreCase("Crypto")) {
            return new Crypto();

        } else if (paymentMethod.equalsIgnoreCase("CreditCard"))    {
            return new CreditCard();

        } else if (paymentMethod.equalsIgnoreCase("DebitCard")) {
            return new DebitCard();

        }
        return null;
    }
}
