package com.mzdhr.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohammad on 26/02/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private static final String TAG = MovieAdapter.class.getSimpleName();
    //private static int viewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private ArrayList<Movie> mMovies;
    private Context mContext;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public MovieAdapter(ArrayList<Movie> movies, Context context, ListItemClickListener listener){
        mMovies = movies;
        mContext = context;
        mOnClickListener = listener;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }



    class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener{
        ImageView posterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.poster_imageView);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            Picasso.with(mContext)
                    .load(mMovies.get(position).getPosterUrl())
                    .placeholder(R.drawable.loadingposter)
                    .error(R.drawable.noposteravailable)
                    .into(posterImageView);
            //posterImageView.setImageResource(R.drawable.demo_poster);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
