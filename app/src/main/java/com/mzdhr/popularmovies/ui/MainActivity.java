package com.mzdhr.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.adapter.MovieAdapter;
import com.mzdhr.popularmovies.helper.Constant;
import com.mzdhr.popularmovies.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Movie> mMovies = new ArrayList<>();

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

        // Setting RecyclerView
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        MovieAdapter adapter = new MovieAdapter(mMovies, this);
        mRecyclerView.setAdapter(adapter);

        // Demo Data
        for (int i = 0; i < 20; i++) {
            Movie movie = new Movie("Title", "url", "33-33-2018", "8", "this is jsut a plot test for this movie");
            mMovies.add(movie);
        }

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
        intent.putExtra(Constant.MOVIE_PLOT, mMovies.get(clickedItemIndex).getPosterUrl());
        startActivity(intent);
    }
}
