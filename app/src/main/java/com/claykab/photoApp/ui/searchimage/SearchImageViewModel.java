package com.claykab.photoApp.ui.searchimage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.ui.home.HomeRepository;

public class SearchImageViewModel extends AndroidViewModel {

    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;
    private SearchImageRepository searchImageRepository;

    public SearchImageViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        searchImageRepository= new SearchImageRepository();

    }



    public LiveData<PictureResponse> searchPictures(String query){


        pictureResponseMediatorLiveData.addSource(searchImageRepository.getPictures(query), pictureResponse -> pictureResponseMediatorLiveData.setValue(pictureResponse));
        return pictureResponseMediatorLiveData;
    }
}