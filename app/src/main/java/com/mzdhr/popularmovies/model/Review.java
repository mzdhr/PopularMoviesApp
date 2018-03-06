package com.mzdhr.popularmovies.model;

/**
 * Created by mohammad on 05/03/2018.
 */

public class Review {
    String mAuthorName;
    String mReviewContent;

    public Review(String authorName, String reviewContent) {
        mAuthorName = authorName;
        mReviewContent = reviewContent;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public String getReviewContent() {
        return mReviewContent;
    }

    public void setReviewContent(String reviewContent) {
        mReviewContent = reviewContent;
    }
}
