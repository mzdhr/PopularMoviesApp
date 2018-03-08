package com.mzdhr.popularmovies.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.adapter.FavoriteAdapter;
import com.mzdhr.popularmovies.database.DatabaseContract;

public class FavoriteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    // Objects
    private static final String TAG = FavoriteActivity.class.getSimpleName();
    private static final int ALL_FAVORITE_MOVIES_LOADER_ID = 444;
    private FavoriteAdapter mAdapter = null;

    // Views
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();

        // Preparing Adapter, RecyclerView
        //mAdapter = new FavoriteAdapter(this, null);
        //mRecyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(ALL_FAVORITE_MOVIES_LOADER_ID, null, this);
    }

    private void findViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.favoriteRecyclerView);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                DatabaseContract.MovieEntry._ID,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_TITLE,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_POSTER_IMAGE_URL,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_RATING,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_PLOT,
                DatabaseContract.MovieEntry.COLUMN_MOVIE_MOVIE_ID
        };

        return new CursorLoader(this,
                DatabaseContract.MovieEntry.CONTENT_URI_MOVIE,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter = new FavoriteAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
