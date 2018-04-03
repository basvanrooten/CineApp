package com.avans2018.klasd.cineapp.data_access_layer.seat;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Seat;
import com.avans2018.klasd.cineapp.domain_layer.SeatStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jorrit on 31-3-2018.
 */

public class GetSeatStatusTask extends AsyncTask<String, Void, String> {

    private GetSeatStatusListener getSeatStatusListener;
    private MovieSchedule movieSchedule;
    private Seat seat;

    public GetSeatStatusTask(GetSeatStatusListener getSeatStatusListener, MovieSchedule movieSchedule, Seat seat) {
        this.getSeatStatusListener = getSeatStatusListener;
        this.movieSchedule = movieSchedule;
        this.seat = seat;
    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader bufferedReader = null;
        String response = "";

        try {
            URL url = new URL("http://api.gaikvanavondlam.nl/getseatbyseatidandscheduleid?scheduleId="+this.movieSchedule.getId()+"&seatId=" + this.seat.getSeatId());
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

            JSONArray results = jsonObject.getJSONArray("results");

            Log.i(getClass().getSimpleName(), "Recieved " + results.length() + " objects");

            for (int i = 0; i < results.length(); i++) {
                JSONObject object = results.getJSONObject(i);
                this.seat.setStatus(SeatStatus.getSeatByStatus(object.getInt("taken")));

            }

            getSeatStatusListener.onSeatRecieved(this.seat);


        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}
