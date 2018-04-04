package com.avans2018.klasd.cineapp.data_access_layer.mollie;

import com.avans2018.klasd.cineapp.domain_layer.Payment;

public interface PaymentListener {

    void onPaymentReceived(Payment p);
}
