package com.example.advancedsoftwareengineering.ui.userhome;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserHomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UserHomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}