package com.avans2018.klasd.cineapp.data_access_layer.movieschedule;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.application_logic_layer.StringKeys;
import com.avans2018.klasd.cineapp.data_access_layer.movie.MovieTask;
import com.avans2018.klasd.cineapp.domain_layer.Movie;
import com.avans2018.klasd.cineapp.domain_layer.MovieSchedule;
import com.avans2018.klasd.cineapp.domain_layer.Theater;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gebruiker on 28-3-2018.
 */

public class GetMovieSchedulesTask extends AsyncTask<String, Void, String> {

    private GetMovieSchedulesListener getMovieSchedulesListener;
    private Movie movie;
    private Date date;

    public GetMovieSchedulesTask(GetMovieSchedulesListener getMovieSchedulesListener, Movie movie, Date date) {
        this.getMovieSchedulesListener = getMovieSchedulesListener;
        this.movie = movie;
        this.date = date;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader bufferedReader = null;
        String response = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);

        try {
            URL url = new URL("http://api.gaikvanavondlam.nl/calls/getMovieSchedulesByMovieIdAndDate.php?movieId="+movie.getId()+"&startDate="+dateStr);
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

        ArrayList<MovieSchedule> movieSchedules = new ArrayList<>();

        Log.i(getClass().getSimpleName(), "async response data : " + response);

        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONArray results = jsonObject.getJSONArray("results");

            Log.i(getClass().getSimpleName(), "Recieved " + results.length() + " objects");

            for (int i = 0; i < results.length(); i++) {
                JSONObject object = results.getJSONObject(i);
                int id = object.getInt("id");
                int takenPerc = object.getInt("takenPerc");
//                Date startDate = new Date(object.getString("startDate"));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = df.parse(object.getString("startDate"));
                final long ONE_MINUTE_IN_MILLIS = 60000;
                long startTime = startDate.getTime();
                Date endDate = new Date(startTime + (this.movie.getDuration() * ONE_MINUTE_IN_MILLIS));


                JSONObject theaterObject = object.getJSONObject("theater");
                int theaterId = theaterObject.getInt("id");

                String theaterName = theaterObject.getString("name");

                Theater theater = new Theater(theaterId, theaterName);

                  movieSchedules.add(new MovieSchedule(id, startDate, this.movie, theater, endDate, takenPerc));

            }

            getMovieSchedulesListener.onMovieSchedulesRecieved(movieSchedules);

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

    }

}
