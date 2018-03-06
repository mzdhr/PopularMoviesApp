package com.mzdhr.popularmovies.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.adapter.ReviewAdapter;
import com.mzdhr.popularmovies.adapter.TrailerAdapter;
import com.mzdhr.popularmovies.helper.Constant;
import com.mzdhr.popularmovies.helper.JsonUtils;
import com.mzdhr.popularmovies.helper.NetworkUtils;
import com.mzdhr.popularmovies.model.Review;
import com.mzdhr.popularmovies.model.Trailer;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    // Objects
    private static final String TAG = DetailsActivity.class.getSimpleName();
    private ArrayList<Review> mReviews = new ArrayList<>();
    private ArrayList<Trailer> mTrailers = new ArrayList<>();
    private TrailerAdapter mTrailerAdapter;
    private ReviewAdapter mReviewAdapter;
    private String mTitle;
    private String mPosterLink;
    private String mReleaseDate;
    private String mRating;
    private String mPlot;
    private String mMovieID;

    // Views
    private ImageView mPosterImageView;
    private TextView mTitleTextView;
    private TextView mReleaseDateTextView;
    private TextView mRatingTextView;
    private TextView mPlotTextView;
    private RecyclerView mTrailerRecyclerView;
    private RecyclerView mReviewsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();

        Intent intent = getIntent();
        if (intent.hasExtra(Constant.MOVIE_TITLE)) {
            mTitle = intent.getStringExtra(Constant.MOVIE_TITLE);
            mPosterLink = intent.getStringExtra(Constant.MOVIE_POSTER);
            mReleaseDate = intent.getStringExtra(Constant.MOVIE_RELEASE_DATE);
            mRating = intent.getStringExtra(Constant.MOVIE_RATING);
            mPlot = intent.getStringExtra(Constant.MOVIE_PLOT);
            mMovieID = intent.getStringExtra(Constant.MOVIE_ID);
            getSupportActionBar().setTitle(mTitle);
            bindData();
        }

        // Setting RecyclerViews
        LinearLayoutManager trailerLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mTrailerRecyclerView.setLayoutManager(trailerLinearLayoutManager);
        mTrailerRecyclerView.setHasFixedSize(true);
        getDataTrailers(Constant.SORT_BY_MOVIE_TRAILER);
        setAdapterTrailer();

        LinearLayoutManager reviewLinearLayoutManager = new LinearLayoutManager(this);
        mReviewsRecyclerView.setLayoutManager(reviewLinearLayoutManager);
        mReviewsRecyclerView.setHasFixedSize(false);
        mReviewsRecyclerView.setNestedScrollingEnabled(false);
        getDataReviews(Constant.SORT_BY_MOVIE_REVIEW);
        setAdapterReviews();


        // TODO: 06/03/2018 need to set them in the adapter when the load data
        mTrailerRecyclerView.setFocusable(false);
        mReviewsRecyclerView.setFocusable(false);
    }

    private void findViews() {
        mPosterImageView = (ImageView) findViewById(R.id.movie_poster_imageView);
        mTitleTextView = (TextView) findViewById(R.id.movie_title_textView);
        mReleaseDateTextView = (TextView) findViewById(R.id.movie_release_date_textView);
        mRatingTextView = (TextView) findViewById(R.id.movie_vote_textView);
        mPlotTextView = (TextView) findViewById(R.id.movie_plot_textView);
        mTrailerRecyclerView = (RecyclerView) findViewById(R.id.trailerRecyclerView);
        mReviewsRecyclerView = (RecyclerView) findViewById(R.id.reviewRecyclerView);


    }

    private void bindData() {
        Picasso.with(this)
                .load(mPosterLink)
                .placeholder(R.drawable.loadingposter)
                .error(R.drawable.noposteravailable)
                .into(mPosterImageView);

        mTitleTextView.setText(mTitle);
        mReleaseDateTextView.setText(mReleaseDate);
        mRatingTextView.setText(mRating);
        mPlotTextView.setText(mPlot);
    }

    private void setAdapterTrailer(){
        mTrailerAdapter = new TrailerAdapter(mTrailers, this);
        mTrailerRecyclerView.setAdapter(mTrailerAdapter);
    }

    private void setAdapterReviews(){
       mReviewAdapter = new ReviewAdapter(mReviews, this);
        mReviewsRecyclerView.setAdapter(mReviewAdapter);
    }

    private void getDataTrailers(String sortType){
        URL url = NetworkUtils.buildURL(sortType, mMovieID);
        Log.d(TAG, "getDataTrailers: " + url.toString());
        new TrailerQueryTask().execute(url);
    }

    private void getDataReviews(String sortType){
        URL url = NetworkUtils.buildURL(sortType, mMovieID);
        Log.d(TAG, "getDataReviews: " + url.toString());
        new ReviewsQueryTask().execute(url);
    }


    /**
     * Async Task Classes as to fetch data in background
     */

    public class TrailerQueryTask extends AsyncTask<URL, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //mTrailerProgressBar.setVisibility(View.VISIBLE);
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
            //mTrailerProgressBar.setVisibility(View.INVISIBLE);

            if (result != null && !TextUtils.isEmpty(result)) {
                try {
                    mTrailers.clear();
                    mTrailers = JsonUtils.parseMovieTrailersJSON(result);
                    setAdapterTrailer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // showErrorMessage();
            }
        }
    }


    public class ReviewsQueryTask extends AsyncTask<URL, Void, String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //mReviewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls){
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
            //mReviewProgressBar.setVisibility(View.INVISIBLE);
            if (result != null && !TextUtils.isEmpty(result)) {
                try {
                    mReviews.clear();
                    mReviews = JsonUtils.parseMovieReviewsJSON(result);
                    setAdapterReviews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // showErrorMessage();
            }
        }
    }

}
