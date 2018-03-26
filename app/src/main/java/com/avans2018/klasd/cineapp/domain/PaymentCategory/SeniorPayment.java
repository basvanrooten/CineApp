package com.avans2018.klasd.cineapp.domain.PaymentCategory;

/**
 * Created by HeyRobin on 26-3-2018.
 *  * Last Edited by Robin on 26-03-18.
 */

public class SeniorPayment implements PaymentCategory {

    private final double PRICE = 6.50;

    @Override
    public double getPrice() {
        return PRICE;
    }
}
