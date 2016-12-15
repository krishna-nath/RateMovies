package com.example.krishna.ratemoviez;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by krishna on 20/9/16.
 */
public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {
    private Bitmap[] mImages;
    private String[] overview;
    private String[] title;
    private boolean[] isfav;
    private Context mContext;
    private int  mLength;
    private CallbackInterface mCall;
    private ProgressDialog mProgressBar;


    AsyncHttpTask(CallbackInterface mCall,Context mContext) {
       this.mCall=mCall;
       this.mContext=mContext;

    }



    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        HttpURLConnection conn;
        String jsondata = null;
        Integer mFlag = 0;

        try {
            URL url = new URL(params[0]);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();


            if (response == 200) {

                StringBuffer sb = new StringBuffer();
                int chr;
                Log.d("tag", "The response is: " + response);
                is = conn.getInputStream();

                while ((chr = is.read()) != -1) {
                    sb.append((char) chr);
                }

                jsondata = sb.toString();

                parseData(jsondata);
                mFlag = 200;


            }

            if (response == 401) {
                mFlag = 401;
            }
            if (response == -1) {
                mFlag = -1;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return mFlag;
    }

    private void parseData(String jsondata) {
        String base_url = "http://image.tmdb.org/t/p/w500/";


        JSONObject jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(jsondata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = jsonRootObject.optJSONArray("results");
        mLength=jsonArray.length();
        mImages = new Bitmap[mLength];
        overview= new String[mLength];
        title=new String[mLength];
        isfav=new boolean[mLength];
        String poster_url = null;



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
               poster_url="";
            } catch (JSONException e) {
                e.printStackTrace();
            }

                poster_url = base_url + jsonObject.optString("poster_path").toString();
            try {
                overview[i] = jsonObject.getString("overview");
                title[i] = jsonObject.getString("title").toString();
                isfav[i] = false;
            }catch (JSONException e){
                e.printStackTrace();
            }


            try {

                URL durl = new URL(poster_url);
                mImages[i] = BitmapFactory.decodeStream(durl.openConnection().getInputStream());


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onPostExecute(Integer flag) {
        super.onPostExecute(flag);

        if (flag == 200) {
            ArrayList<MoviePoster> movieposters = new ArrayList<>();
            for (int i = 0; i < mImages.length; i++) {
                MoviePoster mMovie = new MoviePoster();
                mMovie.setImage(mImages[i]);
                mMovie.setmMovieoverview(overview[i]);
                mMovie.setmIsfavourite(isfav[i]);
                mMovie.setmMovietitle(title[i]);

                movieposters.add(mMovie);

            }


               mCall.returnMovies(movieposters);

        }
        else if(flag==401) {

            Toast.makeText(mContext.getApplicationContext(),"Page not found",Toast.LENGTH_SHORT).show();

        }
        else {

            Toast.makeText(mContext.getApplicationContext(),"Server error",Toast.LENGTH_SHORT).show();

        }


    }



    }


