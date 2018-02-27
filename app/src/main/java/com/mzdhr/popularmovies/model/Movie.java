package com.mzdhr.popularmovies.model;

/**
 * Created by mohammad on 26/02/2018.
 */

public class Movie {
    String mTitle;
    String mPosterUrl;
    String mReleaseDate;
    String mRating;
    String mPlot;

    public Movie(String title, String posterUrl, String releaseDate, String rating, String plot) {
        mTitle = title;
        mPosterUrl = posterUrl;
        mReleaseDate = releaseDate;
        mRating = rating;
        mPlot = plot;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getPosterUrl() {
        return "http://image.tmdb.org/t/p/w185/" + mPosterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        mPosterUrl = posterUrl;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }
}
