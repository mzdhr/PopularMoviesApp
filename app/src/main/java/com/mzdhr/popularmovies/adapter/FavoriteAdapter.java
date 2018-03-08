package com.mzdhr.popularmovies.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.database.DatabaseContract;

/**
 * Created by mohammad on 06/03/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    // Objects
    private static final String TAG = FavoriteAdapter.class.getSimpleName();
    private Context mContext;
    public Cursor mCursor;

    public FavoriteAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        Log.d(TAG, "FavoriteAdapter: mCursor count:" + mCursor.getCount());
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_favorite, parent, false);
        FavoriteViewHolder viewHolder = new FavoriteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView mMoviePosterImageView;
        TextView mMovieTitleTextView;
        TextView mMovieReleaseDateTextView;
        RatingBar mRatingBar;


        public FavoriteViewHolder(View itemView) {
            super(itemView);
            // FindViews
            mMoviePosterImageView = itemView.findViewById(R.id.movie_poster_imageView);
            mMovieTitleTextView = itemView.findViewById(R.id.movie_title_textView);
            mMovieReleaseDateTextView = itemView.findViewById(R.id.movie_release_date_textView);
            mRatingBar = itemView.findViewById(R.id.ratingBar);
        }

        void bind(int position) {
            // Getting Indexes
            mCursor.moveToPosition(position);
            int moviePosterIndex = mCursor.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_MOVIE_POSTER_IMAGE);
            int movieTitleIndex = mCursor.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_MOVIE_TITLE);
            int movieRatingIndex = mCursor.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_MOVIE_RATING);
            int movieReleaseDateIndex = mCursor.getColumnIndex(DatabaseContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE);

            // Getting Values
            byte[] posterBlob = mCursor.getBlob(moviePosterIndex);
            Bitmap posterBitmap = BitmapFactory.decodeByteArray(posterBlob, 0, posterBlob.length);
            String title = mCursor.getString(movieTitleIndex);
            String rating = mCursor.getString(movieRatingIndex);
            String releaseDate = mCursor.getString(movieReleaseDateIndex);

            // Setting Value
            Log.d(TAG, "bind:" + posterBitmap);
            mMoviePosterImageView.setImageBitmap(posterBitmap);
            mMovieTitleTextView.setText(title);
            mRatingBar.setRating(Float.parseFloat(rating));
            mMovieReleaseDateTextView.setText(releaseDate);

        }
    }


}
