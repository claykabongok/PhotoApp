package com.claykab.photoApp.ui.searchimage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.network.RetrofitClient;
import com.claykab.photoApp.utils.API_KEY;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchImageRepository {
    public LiveData<PictureResponse> getPictures(String query){
        String key= API_KEY.API_KEY;
        String image_type="photo";
        int per_page=75;
        //String key,String image_type,int per_page
        final MutableLiveData<PictureResponse> pictureResponseMutableLiveData= new MutableLiveData<>();

        Call<PictureResponse> call= RetrofitClient.getInstance().get_Pictures_API().searchPictures(key,image_type,per_page,true,query);
        call.enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, Response<PictureResponse> response) {

                if(response.isSuccessful()){
                    pictureResponseMutableLiveData.postValue( response.body());
                }
                else {
                    pictureResponseMutableLiveData.postValue(new PictureResponse(response.code(), response.message()));

                }



            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                pictureResponseMutableLiveData.postValue(new PictureResponse(t));

            }
        });
        return pictureResponseMutableLiveData;
    }

}

