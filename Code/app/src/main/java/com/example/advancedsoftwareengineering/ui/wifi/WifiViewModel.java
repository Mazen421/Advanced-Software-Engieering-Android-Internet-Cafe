package com.example.advancedsoftwareengineering.ui.wifi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WifiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WifiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cafe fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}