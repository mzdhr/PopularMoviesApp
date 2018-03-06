package com.mzdhr.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mzdhr.popularmovies.R;
import com.mzdhr.popularmovies.model.Review;

import java.util.ArrayList;

/**
 * Created by mohammad on 26/02/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private static final String TAG = ReviewAdapter.class.getSimpleName();
    //private static int viewHolderCount;
    private ArrayList<Review> mReviews;
    private Context mContext;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    public ReviewAdapter(ArrayList<Review> reviews, Context context){
        mReviews = reviews;
        mContext = context;
    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie_review, parent, false);
        ReviewViewHolder viewHolder = new ReviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }



    class ReviewViewHolder extends RecyclerView.ViewHolder implements OnClickListener{
        TextView authorTextView;
        TextView contentTextView;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            authorTextView = (TextView) itemView.findViewById(R.id.review_author_name_textView);
            contentTextView = (TextView) itemView.findViewById(R.id.review_user_content_textView);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            authorTextView.setText(mReviews.get(position).getAuthorName());
            contentTextView.setText(mReviews.get(position).getReviewContent());
        }

        @Override
        public void onClick(View v) {

        }
    }

}
