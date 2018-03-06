package com.mzdhr.popularmovies.model;

/**
 * Created by mohammad on 05/03/2018.
 */

public class Trailer {
    String mMoviekey;

    public Trailer(String moviekey) {
        mMoviekey = moviekey;
    }

    public String getMoviekey() {
        return mMoviekey;
    }

    public void setMoviekey(String moviekey) {
        mMoviekey = moviekey;
    }

}
