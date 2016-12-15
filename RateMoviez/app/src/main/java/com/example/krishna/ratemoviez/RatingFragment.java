package com.example.krishna.ratemoviez;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RatingFragment extends Fragment implements CallbackInterface,CommunicatorInterface{
    private RecyclerView mRecyclerView;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.tab2, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.card_recycler_view_tab2);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);

        return mView;
    }

    private void initLayout() {



        prepareData();

    }

    private void prepareData() {
        final  AsyncHttpTask ratingasync = new AsyncHttpTask(this,getActivity());
        ratingasync.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=a3851049b6e60b2f8a9d8c0ce1945318");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }




    @Override
    public void returnMovies(ArrayList<MoviePoster> moviearray) {

        DataAdapter adapter = new DataAdapter(getActivity(),moviearray,this);
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    public void passTo(Bundle item) {

    }
}
