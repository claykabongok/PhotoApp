package com.claykab.photoApp.ui.imageDetails;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class imageDetailsRepository {
    /**
     * retrofit call to retrieve a single picture
     * @param key
     * @param id
     * @return
     */
    public LiveData<PictureResponse> getPictureById(String key, long id){
        final MutableLiveData<PictureResponse> pictureResponseMutableLiveData= new MutableLiveData<>();

        Call<PictureResponse> call= RetrofitClient.getInstance().get_Pictures_API().getPicturesById(key, id);
        call.enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, Response<PictureResponse> response) {

                pictureResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {

                pictureResponseMutableLiveData.postValue( new PictureResponse(t));

            }
        });

        return pictureResponseMutableLiveData;
    }


}
