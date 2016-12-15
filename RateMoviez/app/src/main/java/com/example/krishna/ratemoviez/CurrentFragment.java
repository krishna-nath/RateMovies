package com.example.krishna.ratemoviez;

import android.app.Activity;
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

/**
 * Created by krishna on 19/9/16.
 */
public class CurrentFragment extends Fragment implements CallbackInterface,CommunicatorInterface {
    private CheckConnection mChecknet;
    private RecyclerView mRecyclerView;
    private ActivityInterface mConnector;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.tab1, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.card_recycler_view_tab1);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(layoutManager);

        return mView;
    }

    private void initLayout() {



        prepareData();

    }

    private void prepareData() {
        final  AsyncHttpTask asyncTask = new AsyncHttpTask(this,getActivity());

        asyncTask.execute("http://api.themoviedb.org/3/movie/popular?api_key=a3851049b6e60b2f8a9d8c0ce1945318");


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
        mConnector= (ActivityInterface) getActivity();
        mConnector.passToActivity(item);

    }
}
