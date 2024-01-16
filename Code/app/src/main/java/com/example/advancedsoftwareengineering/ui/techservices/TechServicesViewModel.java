package com.example.advancedsoftwareengineering.ui.techservices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TechServicesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TechServicesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}