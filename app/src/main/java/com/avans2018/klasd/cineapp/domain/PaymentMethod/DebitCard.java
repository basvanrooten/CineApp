package com.avans2018.klasd.cineapp.domain.PaymentMethod;

/**
 * Created by HeyRobin on 26-3-2018.
 */

public class DebitCard implements PaymentMethod {
    @Override
    public boolean withdrawalAmount(int amount) {
        return true;
    }
}
