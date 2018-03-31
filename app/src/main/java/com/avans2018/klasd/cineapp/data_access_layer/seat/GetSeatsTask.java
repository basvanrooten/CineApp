package com.avans2018.klasd.cineapp.data_access_layer.seat;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Seat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Jorrit on 31-3-2018.
 */

public class GetSeatsTask extends AsyncTask<String, Void, String> {

    private GetSeatsListener getSeatsListener;
    private MovieSchedule movieSchedule;

    public GetSeatsTask(GetSeatsListener getSeatsListener, MovieSchedule movieSchedule) {
        this.getSeatsListener = getSeatsListener;
        this.movieSchedule = movieSchedule;
    }

    @Override
    protected String doInBackground(String... strings) {
        BufferedReader bufferedReader = null;
        String response = "";

        try {
            URL url = new URL("http://api.gaikvanavondlam.nl/getseatsbyscheduleid?scheduleId=" + this.movieSchedule.getId());
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

            JSONArray results = jsonObject.getJSONArray("results");

            Log.i(getClass().getSimpleName(), "Recieved " + results.length() + " objects");

            for (int i = 0; i < results.length(); i++) {
                JSONObject object = results.getJSONObject(i);
                int seatId = object.getInt("seatId");
                int theaterId = object.getInt("theaterId");
                int seatNumber = object.getInt("seatNumber");
                int rowNumber = object.getInt("rowNumber");
                int taken = object.getInt("taken");

                seats.add(new Seat(seatId, seatNumber, rowNumber, movieSchedule.getTheater(), taken));

            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

}
