package com.claykab.photoApp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkState {
    /**
     * Check network state
     */
    public static Boolean isDeviceConnected(Context mContext){
        /**
         * Detect whether the device is connected to the mobile or Wifi network
         *
         */
        ConnectivityManager connectivityManager=(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=activeNetwork != null && activeNetwork.isConnectedOrConnecting();


        return  isConnected;

    }


}
