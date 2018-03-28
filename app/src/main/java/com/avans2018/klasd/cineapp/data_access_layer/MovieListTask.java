package com.avans2018.klasd.cineapp.data_access_layer;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.application_logic_layer.StringKeys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Gebruiker on 27-3-2018.
 */

public class MovieListTask extends AsyncTask<String, Void, String> {

    private MovieListener movieListener;

    public MovieListTask(MovieListener movieListener) {
        this.movieListener = movieListener;
    }

    @Override
    protected String doInBackground(String... params) {

        BufferedReader bufferedReader = null;
        String response = "";

        try {
            java.net.URL url = new URL("https://api.themoviedb.org/3/movie/now_playing?api_key=" + StringKeys.API_KEY);
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
                String id = object.getString("id");

                MovieTask task = new MovieTask(movieListener);
                task.execute(id);

            }

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

    }

}