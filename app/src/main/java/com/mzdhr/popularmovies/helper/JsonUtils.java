package com.mzdhr.popularmovies.helper;

import com.mzdhr.popularmovies.model.Movie;
import com.mzdhr.popularmovies.model.Review;
import com.mzdhr.popularmovies.model.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mohammad on 27/02/2018.
 * This class to help extracting data from JSON format into Movie model.
 *
 * Demo JSON DATA:
 *
 */

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName();

    public static ArrayList<Movie> parseMovieJSON(String json) throws JSONException {
        // Preparing movie list
        ArrayList<Movie> movies = new ArrayList<>();

        // Creating JSON Objects and Arrays
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonObjectResult = jsonObject.getJSONArray("results");

        for (int i = 0; i < jsonObjectResult.length(); i++) {
            JSONObject movieObjectJSON = (JSONObject) jsonObjectResult.get(i);
            Movie movie = new Movie(
                    movieObjectJSON.getString("title"),
                    movieObjectJSON.getString("poster_path"),
                    movieObjectJSON.getString("release_date"),
                    movieObjectJSON.getString("vote_average"),
                    movieObjectJSON.getString("overview"),
                    movieObjectJSON.getString("id")
                    );

            movies.add(movie);
        }
        return movies;
    }

    // TODO: 05/03/2018 this method
    public static ArrayList<Trailer> parseMovieTrailersJSON(String json) throws JSONException {
        // Preparing trailer list
        ArrayList<Trailer> trailers = new ArrayList<>();

        // Creating JSON Objects and Arrays
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonObjectResult = jsonObject.getJSONArray("results");

        for (int i = 0; i < jsonObjectResult.length(); i++) {
            JSONObject movieObjectJSON = (JSONObject) jsonObjectResult.get(i);
            Trailer trailer = new Trailer(
                    movieObjectJSON.getString("key")
            );

            trailers.add(trailer);
        }
        return trailers;
    }

    // TODO: 05/03/2018 this method
    public static ArrayList<Review> parseMovieReviewsJSON(String json) throws JSONException {
        // Preparing review list
        ArrayList<Review> reviews = new ArrayList<>();

        // Creating JSON Objects and Arrays
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonObjectResult = jsonObject.getJSONArray("results");

        for (int i = 0; i < jsonObjectResult.length(); i++) {
            JSONObject movieObjectJSON = (JSONObject) jsonObjectResult.get(i);
            Review review = new Review(
                    movieObjectJSON.getString("author"),
                    movieObjectJSON.getString("content")

            );

            reviews.add(review);
        }
        return reviews;
    }
}