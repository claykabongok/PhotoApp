package com.claykab.photoApp.ui.categoryview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.network.RetrofitClient;
import com.claykab.photoApp.utils.API_KEY;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewRepository {
    public LiveData<PictureResponse> getPictures(String category){
        String key= API_KEY.API_KEY;
        String image_type="photo";
        int per_page=75;
        //String key,String image_type,int per_page
        final MutableLiveData<PictureResponse> pictureResponseMutableLiveData= new MutableLiveData<>();

        Call<PictureResponse> call= RetrofitClient.getInstance().get_Pictures_API().getPicturesCat(key,category,image_type,per_page,true);
        call.enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(Call<PictureResponse> call, Response<PictureResponse> response) {

                pictureResponseMutableLiveData.postValue( response.body());


            }

            @Override
            public void onFailure(Call<PictureResponse> call, Throwable t) {
                pictureResponseMutableLiveData.postValue(new PictureResponse(t));

            }
        });
        return pictureResponseMutableLiveData;
    }
}
