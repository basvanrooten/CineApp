package com.avans2018.klasd.cineapp.data_access_layer.mollie;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.Payment;
import com.avans2018.klasd.cineapp.domain_layer.Seat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GetPaymentTask extends AsyncTask<String, Void, String> {

    private PaymentListener paymentListener;
    private Payment payment;

    public GetPaymentTask(PaymentListener paymentListener, Payment payment) {
        this.paymentListener = paymentListener;
        this.payment = payment;
    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader bufferedReader = null;
        String response = "";

        try {
            URL url = new URL("http://api.gaikvanavondlam.nl/getPayment?id="+payment.getId());
            URLConnection connection = url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            response = "";

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(getClass().getSimpleName(), e.getMessage());
                    return null;
                }
            }
        }

        return response;
    }
    @Override
    protected void onPostExecute(String response) {

        Log.i(getClass().getSimpleName(), "async response data : " + response);

        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject result = jsonObject.getJSONObject("payment");

            String status = result.getString("status");
            this.payment.setStatus(status);
            paymentListener.onPaymentReceived(this.payment);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}
