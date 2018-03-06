package com.mzdhr.popularmovies.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mohammad on 06/03/2018.
 */

public class DatabaseContract {

    private DatabaseContract(){}

    // Content Provider
    public static final String CONTENT_AUTHORITY = "com.mzdhr.popularmovies";

    // Base Provider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible Paths
    public static final String PATH_MOVIES = "movies";


    public static final class MovieEntry implements BaseColumns {

        // Movie Table Uri
        public static final Uri CONTENT_URI_MOVIE = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MOVIES);

        // Movie Table
        public static final String _ID = BaseColumns._ID;
        public static final String MOVIE_TABLE_NAME = "movie_table_name";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_POSTER_URL_LOCAL = "poster";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_RATING = "rating";
        public static final String COLUMN_MOVIE_PLOT = "plot";
        public static final String COLUMN_MOVIE_MOVIE_ID = "movie_id";

    }

}
