package com.avans2018.klasd.cineapp.data_access_layer;

import android.os.AsyncTask;
import android.util.Log;

import com.avans2018.klasd.cineapp.application_logic.StringKeys;
import com.avans2018.klasd.cineapp.domain.Movie;

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

public class MovieTask extends AsyncTask<String, Void, String> {

    private MovieListener movieListener;

    public MovieTask(MovieListener movieListener) {
        this.movieListener = movieListener;
    }

    @Override
    protected String doInBackground(String... strings) {

        BufferedReader bufferedReader = null;
        String response = "";

        if(strings.length == 0){
            throw new IllegalArgumentException("No parameters given in the execute method!");
        }

        String id = strings[0];

        try { //&language=nl_NL
            java.net.URL url = new URL("https://api.themoviedb.org/3/movie/"+id+"?api_key="+ StringKeys.API_KEY);
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


            Log.i(getClass().getSimpleName(), "Recieved " + jsonObject.toString());

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("title");
            boolean adultOnly = jsonObject.getBoolean("adult");
            String genre = "";

            JSONArray genres = jsonObject.getJSONArray("genres");

            for(int i = 0; i<genres.length(); i++){
                genre += genres.getJSONObject(i).getString("name");
            }

            String imageUrl = "http://image.tmdb.org/t/p/w185"+jsonObject.getString("backdrop_path");
            int duration = jsonObject.getInt("runtime");
            String info = jsonObject.getString("overview");
            String language = jsonObject.getString("original_language");
            String releaseDate = jsonObject.getString("release_date");
            String homePageUrl = jsonObject.getString("homepage");
            String status = jsonObject.getString("status");
            int rating = jsonObject.getInt("vote_average");
            int ratingCount = jsonObject.getInt("vote_count");

            Movie movie = new Movie(id, name, adultOnly, genre, imageUrl, duration, info, language, releaseDate, homePageUrl, status, rating, ratingCount);

            movieListener.onMovieRecieved(movie);

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}
