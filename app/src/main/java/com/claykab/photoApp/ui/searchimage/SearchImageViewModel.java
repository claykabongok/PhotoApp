package com.claykab.photoApp.ui.searchimage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchImageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchImageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is searchimage fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}