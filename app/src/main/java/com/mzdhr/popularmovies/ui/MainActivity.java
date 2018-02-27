package com.mzdhr.popularmovies.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        try {
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
        } catch (Exception e) {
            Log.d(TAG, "onCreate: exception" + e.toString());
        }

        findViews();


        // Demo Data
//        for (int i = 0; i < 20; i++) {
//            Movie movie = new Movie("Title", "url", "33-33-2018", "8", "this is jsut a plot test for this movie");
//            mMovies.add(movie);
//        }

        // Setting RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(mMovies, this,this);
        mRecyclerView.setAdapter(mAdapter);



        // build the url
        URL url = NetworkUtils.buildURl("vote_average.desc");
        Log.d(TAG, "onCreate: url: " + url.toString());

        // query the url
        new MovieQueryTask().execute(url);

    }

    public void updateData(){
        mAdapter = new MovieAdapter(mMovies, this,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void findViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

    private void showJsonDataView() {
        // First, make sure the error is invisible
       // mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        //mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    public class MovieQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute(){
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
        protected void onPostExecute(String result){
            mProgressBar.setVisibility(View.INVISIBLE);
            if (result != null && !TextUtils.isEmpty(result)){
                showJsonDataView();

                try {
                    mMovies.clear();
                    mMovies = JsonUtils.parseMovieJSON(result);
                    Log.d(TAG, "onPostExecute: " + mMovies.get(1).getPosterUrl());
                    updateData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                //showErrorMessage();
            }
        }














    }

}
