package com.mzdhr.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.helper.Constant;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView mPosterImageView;
    TextView mTitleTextView;
    TextView mReleaseDateTextView;
    TextView mRatingTextView;
    TextView mPlotTextView;
    String mTitle;
    String mPosterLink;
    String mReleaseDate;
    String mRating;
    String mPlot;

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
            getSupportActionBar().setTitle(mTitle);
            bindData();
        }

    }

    private void findViews() {
        mPosterImageView = (ImageView) findViewById(R.id.movie_poster_imageView);
        mTitleTextView = (TextView) findViewById(R.id.movie_title_textView);
        mReleaseDateTextView = (TextView) findViewById(R.id.movie_release_date_textView);
        mRatingTextView = (TextView) findViewById(R.id.movie_vote_textView);
        mPlotTextView = (TextView) findViewById(R.id.movie_plot_textView);
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


}
