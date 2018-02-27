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
 * More params and options for the API values at: https://themoviedb.api-docs.io/3/discover
 */

public class NetworkUtils {
    final static String THEMOVIEDB_API_BASE_URL = "https://api.themoviedb.org/3/movie/now_playing?";
    final static String PARAM_API_KEY = "api_key";  // value: e800e6bcbdfb1860ba3708d7a77d0844
    final static String PARAM_SORT = "sort_by";     // values: default: popularity.desc |||| popularity.asc, popularity.desc, vote_average.asc, vote_average.desc
    final static String PARAM_MAX_PAGE = "page";
    final static String PARAM_LANG = "language";
    //"https://api.themoviedb.org/3/movie/now_playing?api_key=e800e6bcbdfb1860ba3708d7a77d0844&language=en-US&page=1&sort_by=vote_average.desc"

    /**
     * This method to build the full URL to query themoviedb.com api.
     * @param sortBy User chooses this value.
     * @return the full URL for API.
     */
    public static URL buildURl(String sortBy) {
        Uri builtUri = Uri.parse(THEMOVIEDB_API_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, "e800e6bcbdfb1860ba3708d7a77d0844")  // TODO: 27/02/2018 REMOVE IT, BEFORE GIT PUSH // Hi developer replace it with your key
                .appendQueryParameter(PARAM_SORT, sortBy)
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
