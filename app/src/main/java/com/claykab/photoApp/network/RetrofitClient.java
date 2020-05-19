package com.claykab.photoApp.network;

import com.claykab.photoApp.utils.API_KEY;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    /**
     * Singleton class for Retrofit
     */

    String SERVICE_BASE_URL= API_KEY.SERVICE_BASE_URL;
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private  RetrofitClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null){ //retrofit instance not yet created
            mInstance = new RetrofitClient();
        }

        return mInstance;

    }

    public Picture_API_Interface get_Pictures_API(){
        return retrofit.create(Picture_API_Interface.class);
    }

}
