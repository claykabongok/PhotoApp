package com.claykab.photoApp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.model.hits;

public class HomeViewModel extends AndroidViewModel {

    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;
    private HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        homeRepository= new HomeRepository();

    }



    public LiveData<PictureResponse> getPictures(){


        pictureResponseMediatorLiveData.addSource(homeRepository.getPictures(), pictureResponse -> pictureResponseMediatorLiveData.setValue(pictureResponse));
        return pictureResponseMediatorLiveData;
    }

}