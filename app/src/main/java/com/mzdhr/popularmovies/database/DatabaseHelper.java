package com.mzdhr.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mohammad on 06/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Constants
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "popularmovies.db";
    private static final int DATABASE_VERSION = 1;

    // SQL Commands
    private String SQL_CREATE_MOVIE_TABLE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQL_CREATE_MOVIE_TABLE =
                "CREATE TABLE " + DatabaseContract.MovieEntry.MOVIE_TABLE_NAME + " ("
                + DatabaseContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL DEFAULT '', "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_POSTER_URL_LOCAL + " TEXT NOT NULL DEFAULT '', "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL DEFAULT '', "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_RATING + " TEXT NOT NULL DEFAULT '', "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_PLOT + " TEXT NOT NULL DEFAULT '', "
                + DatabaseContract.MovieEntry.COLUMN_MOVIE_MOVIE_ID + " TEXT NOT NULL DEFAULT '');";

                db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newversion) {

    }
}
