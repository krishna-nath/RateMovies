package com.example.krishna.ratemoviez;

import android.graphics.Bitmap;

/**
 * Created by krishna on 21/9/16.
 */
public class MoviePoster {

    private Bitmap mMovieimg;
    private String mMovieoverview;
    private String mMovietitle;
    private Boolean mIsfavourite;

    public Bitmap getImage() {
        return mMovieimg;
    }

    public String getmMovieoverview() {
        return mMovieoverview;
    }

    public void setmMovieoverview(String mMovieoverview) {
        this.mMovieoverview = mMovieoverview;
    }

    public String getmMovietitle() {
        return mMovietitle;
    }

    public void setmMovietitle(String mMovietitle) {
        this.mMovietitle = mMovietitle;
    }

    public Boolean getmIsfavourite() {
        return mIsfavourite;
    }

    public void setmIsfavourite(Boolean mIsfavourite) {
        this.mIsfavourite = mIsfavourite;
    }

    public void setImage(Bitmap mMovie) {
        this.mMovieimg = mMovie;
    }

}
