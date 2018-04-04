package com.avans2018.klasd.cineapp.data_access_layer.mollie;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.Payment;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.SeatStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CreatePaymentTask extends AsyncTask<String, Void, String> {

    private final String apiKey = "test_952f8yVN3jWnyuv2RmSMHVFF3pUm4C";
    private PaymentListener paymentListener;
    private Payment payment;

    public CreatePaymentTask(PaymentListener paymentListener, Payment payment) {
        this.paymentListener = paymentListener;
        this.payment = payment;
    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader bufferedReader = null;
        String response = "";

        try {
            URL url = new URL("http://api.gaikvanavondlam.nl/createPayment?amount="+payment.getAmount()+"&description="+payment.getDescription());
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
        ArrayList<Seat> seats = new ArrayList<>();

        Log.i(getClass().getSimpleName(), "async response data : " + response);

        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONObject result = jsonObject.getJSONObject("payment");

            String paymentId = result.getString("id");
            String redirectUrl = result.getString("redirectUrl");
            String webhookUrl = result.getString("webhookUrl");
            String paymentUrl = result.getString("paymentUrl");
            this.payment.setId(paymentId);
            this.payment.setPaymentUrl(paymentUrl);
            this.payment.setRedirectUrl(redirectUrl);
            this.payment.setWebhookUrl(webhookUrl);
            paymentListener.onPaymentReceived(this.payment);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}
