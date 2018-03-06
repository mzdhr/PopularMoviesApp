package com.mzdhr.popularmovies.helper;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by mohammad on 27/02/2018.
 * This class contains methods to help with request information from the api themoviedb.com
 * <p>
 * More params and options for the API values at: https://themoviedb.api-docs.io/3/movie/
 */

public class NetworkUtils {
    final static String THEMOVIEDB_API_BASE_URL = "https://api.themoviedb.org/3/movie?";
    final static String PARAM_API_KEY = "api_key";
    final static String PARAM_MAX_PAGE = "page";
    final static String PARAM_LANG = "language";
    final static String API = "e800e6bcbdfb1860ba3708d7a77d0844"; // TODO: Enter api key here.


    /**
     * This method to build URL to query themoviedb.com api. to retrieve movie list.
     * @param sortType this is the path value, which user chooses. /movie/popular, /movie/top_rated, /movie/playing_now
     * @return the full URL for API.
     */
    public static URL buildURl(String sortType) {
        Uri builtUri = Uri.parse(THEMOVIEDB_API_BASE_URL).buildUpon()
                .appendPath(sortType)
                .appendQueryParameter(PARAM_API_KEY, API)
                .appendQueryParameter(PARAM_MAX_PAGE, "1")
                .appendQueryParameter(PARAM_LANG, "en-US")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method to build URL to query themoviedb.com api, to retrieve single Movie Trailers and User Reviews.
     *
     * @param sortType this is the path value, which user chooses. /movie/{id}/videos, /movie/{id}/reviews
     * @param movieID
     * @return
     */
    public static URL buildURL(String sortType, String movieID){
        Uri buildTri = Uri.parse(THEMOVIEDB_API_BASE_URL).buildUpon()
                .appendPath(movieID)
                .appendPath(sortType)
                .appendQueryParameter(PARAM_API_KEY, API)
                .appendQueryParameter(PARAM_MAX_PAGE, "1")
                .appendQueryParameter(PARAM_LANG, "en-US")
                .build();

        URL url = null;
        try {
            url = new URL(buildTri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    /**
     *  This method to get the result from HTTP response, by a full build url.
     * @param url takes the full api url.
     * @return returns result as text.
     * @throws IOException
     */
    public static String getResponseFromHttpURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }

        } finally {
            urlConnection.disconnect();
        }
    }

}
