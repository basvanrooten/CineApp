package com.avans2018.klasd.cineapp.domain;

import android.util.Log;

import java.io.Serializable;

/*Created by Robin*/
/*Last Edit 26-03-18 by Robin*/

public class Movie implements Serializable {

    private int id;
    private String name;
    private boolean adultOnly;
    private String genre;
    private String imageUrl;
    private int duration;
    private String info;
    private String language;
    private String releaseDate;
    private String homePageUrl;
    private String status;
    private int rating;
    private int ratingCount;

    private static final String TAG = "Domain: Movie";

    public Movie()  {
        Log.d(TAG, "Movie-Constructor was called");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdultOnly() {
        return adultOnly;
    }

    public void setAdultOnly(boolean adultOnly) {
        this.adultOnly = adultOnly;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    @Override
    public String toString() {
        return "Moviename ='" + name + "'";
    }
}
