package com.claykab.photoApp.ui.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.claykab.photoApp.model.PictureResponse;
import com.claykab.photoApp.ui.home.HomeRepository;

public class CategoryViewModel extends AndroidViewModel {
    private MediatorLiveData<PictureResponse> pictureResponseMediatorLiveData;
    private CategoryRepository categoryRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        pictureResponseMediatorLiveData=new MediatorLiveData<>();
        categoryRepository= new CategoryRepository();

    }

         public LiveData<PictureResponse> getPicturesCat(String category){


              pictureResponseMediatorLiveData.addSource(categoryRepository.getPictures(category), pictureResponse -> pictureResponseMediatorLiveData.setValue(pictureResponse));
              return pictureResponseMediatorLiveData;
          }


}