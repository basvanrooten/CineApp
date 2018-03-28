package com.avans2018.klasd.cineapp.domain_layer.payment_category;

/**
 * Created by HeyRobin on 26-3-2018.
 *  * Last Edited by Tom on 28-03-18. getPaymentMethodString() toegevoegd als eis voor subclasses om te kunnen displayen welk type klant (Child, Student etc.) ticket heeft gekocht en weg te schrijven in DB.
 */

public interface PaymentCategory {
    double getPrice();
    String getPaymentMethodString();
}

