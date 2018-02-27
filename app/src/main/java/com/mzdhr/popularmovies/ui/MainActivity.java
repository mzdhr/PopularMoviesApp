package com.mzdhr.popularmovies.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.adapter.MovieAdapter;
import com.mzdhr.popularmovies.helper.Constant;
import com.mzdhr.popularmovies.helper.JsonUtils;
import com.mzdhr.popularmovies.helper.NetworkUtils;
import com.mzdhr.popularmovies.model.Movie;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViews();

        // Setting RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        getData(Constant.SORT_BY_PLAYING_RIGHT_NOW);
        setAdapter();
    }

    private void findViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void setAdapter(){
        mAdapter = new MovieAdapter(mMovies, this, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getData(String sortType) {
        URL url;
        if (sortType.equals(Constant.SORT_BY_PLAYING_RIGHT_NOW)){
            url = NetworkUtils.buildURl(sortType);
        } else {
            url = NetworkUtils.buildDiscoverURl(sortType);
        }
        Log.d(TAG, "getData: URL: " + url.toString());
        new MovieQueryTask().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_sort_by_popular) {
            getData(Constant.SORT_BY_POPULAR);
            return true;
        }

        if (id == R.id.action_sort_by_highest_rated) {
            getData(Constant.SORT_BY_HIGHEST_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(Constant.MOVIE_TITLE, mMovies.get(clickedItemIndex).getTitle());
        intent.putExtra(Constant.MOVIE_POSTER, mMovies.get(clickedItemIndex).getPosterUrl());
        intent.putExtra(Constant.MOVIE_RATING, mMovies.get(clickedItemIndex).getRating());
        intent.putExtra(Constant.MOVIE_RELEASE_DATE, mMovies.get(clickedItemIndex).getReleaseDate());
        intent.putExtra(Constant.MOVIE_PLOT, mMovies.get(clickedItemIndex).getPlot());
        startActivity(intent);
    }


    public class MovieQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (result != null && !TextUtils.isEmpty(result)) {
                //showJsonDataView();
                try {
                    mMovies.clear();
                    mMovies = JsonUtils.parseMovieJSON(result);
                    setAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }

    }

}
