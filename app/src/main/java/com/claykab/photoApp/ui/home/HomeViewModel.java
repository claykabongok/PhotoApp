package com.claykab.photoApp.ui.home;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.claykab.photoApp.model.PictureResponse;

public class HomeViewModel extends AndroidViewModel {
    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;
    private HomeRepository homeRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        homeRepository= new HomeRepository();
    }



    public LiveData<PictureResponse> getPictures(){
       pictureResponseMediatorLiveData.addSource(homeRepository.getPictures(), new Observer<PictureResponse>() {
           @Override
           public void onChanged(PictureResponse pictureResponse) {
               pictureResponseMediatorLiveData.setValue(pictureResponse);


           }
       });
        return pictureResponseMediatorLiveData;
    }

}