package com.example.krishna.ratemoviez;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by krishna on 20/9/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<MoviePoster> mPosterarray;
    private Context context;
    private CommunicatorInterface mConnector;

    public DataAdapter(Context context,ArrayList<MoviePoster> android,CommunicatorInterface mConnector) {
        this.mPosterarray = android;
        this.context = context;
        this.mConnector=mConnector;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        Bitmap bmp = mPosterarray.get(i).getImage();
        viewHolder.mMovieimg.setImageBitmap(bmp);
        final Bundle item = new Bundle();
        item.putParcelable("image",bmp);
        item.putString("title",mPosterarray.get(i).getmMovietitle());
        item.putString("overview",mPosterarray.get(i).getmMovieoverview());
        viewHolder.mMovieimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConnector.passTo(item);
            }
        });



    }



    @Override
    public int getItemCount() {
        return mPosterarray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView mMovieimg;
        private DetailsInterface mInfo;
        public ViewHolder(View view) {
            super(view);
            mMovieimg = (ImageView) view.findViewById(R.id.img);



        }



    }

}











