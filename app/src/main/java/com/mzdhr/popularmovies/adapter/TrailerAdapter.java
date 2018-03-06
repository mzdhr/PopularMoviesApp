package com.mzdhr.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mohammad on 26/02/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private static final String TAG = TrailerAdapter.class.getSimpleName();
    //private static int viewHolderCount;
    //final private ListItemClickListener mOnClickListener;
    private ArrayList<Trailer> mTrailers;
    private Context mContext;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public TrailerAdapter(ArrayList<Trailer> trailers, Context context, ListItemClickListener listener) {
        mTrailers = trailers;
        mContext = context;
        // mOnClickListener = listener;
    }

    public TrailerAdapter(ArrayList<Trailer> trailers, Context context) {
        mTrailers = trailers;
        mContext = context;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_trailer, parent, false);
        TrailerViewHolder viewHolder = new TrailerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }


    class TrailerViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView imageView;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_trailer_imageView);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            // Youtube link preview thumbnail -> https://img.youtube.com/vi/{YOUTUBE_VIDEO_ID}/0.jpg
            final String videoID = mTrailers.get(position).getMoviekey();
            String url = "http://www.youtube.com/embed/" + videoID;
            String urlThumbnail = "https://img.youtube.com/vi/" + videoID + "/0.jpg";

            Picasso.with(mContext)
                    .load(urlThumbnail)
                    .into(imageView);

            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoID));
                    mContext.startActivity(youtubeIntent);
//                    Intent intent = new Intent()
                    // TODO: 06/03/2018 handel when user not have youtube app installed on his device
                }
            });

        }

        @Override
        public void onClick(View v) {
            //  mOnClickListener.onListItemClick(getAdapterPosition());
        }
    }

}
