package com.claykab.photoApp.ui.imageDetails;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.claykab.photoApp.model.PictureResponse;

public class ImageDetailsViewModel extends AndroidViewModel {

    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;
    private imageDetailsRepository imageDetailsRepository;

    public ImageDetailsViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        imageDetailsRepository= new imageDetailsRepository();

    }



    public LiveData<PictureResponse> getPicturesById(String key, long id){

        pictureResponseMediatorLiveData.addSource(imageDetailsRepository.getPictureById(key, id), pictureDetailsResponse -> pictureResponseMediatorLiveData.setValue(pictureDetailsResponse));

        return pictureResponseMediatorLiveData;
    }

}