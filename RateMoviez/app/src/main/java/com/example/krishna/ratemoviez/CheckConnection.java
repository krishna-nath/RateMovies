package com.example.krishna.ratemoviez;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by krishna on 20/9/16.
 */
public class CheckConnection {
    private ConnectivityManager mConn;
    private Context mContext;

    CheckConnection(Context cxt) {
        this.mContext =cxt;
    }


    public boolean isConnected() {

        mConn = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mConn.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }



}
