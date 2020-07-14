package com.claykab.photoApp.ui.categoryview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.claykab.photoApp.model.PictureResponse;

public class CategoryViewModel extends AndroidViewModel {
    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;

    private CategoryViewRepository categoryViewRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        categoryViewRepository= new CategoryViewRepository();

    }

         public LiveData<PictureResponse> getPicturesCat(String category){


              pictureResponseMediatorLiveData.addSource(categoryViewRepository.getPictures(category), pictureResponse -> pictureResponseMediatorLiveData.setValue(pictureResponse));
              return pictureResponseMediatorLiveData;
          }


}