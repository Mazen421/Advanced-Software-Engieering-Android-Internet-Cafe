package com.example.advancedsoftwareengineering;

import androidx.fragment.app.Fragment;

import com.example.advancedsoftwareengineering.ui.cafe.CafeFragment;
import com.example.advancedsoftwareengineering.ui.home.HomeFragment;

public class FragmentDecider {
    public static Fragment fragmentDecider(String name){
        Fragment fragment = null;
        switch(name){
            case "Home":
                fragment = new HomeFragment();
                break;
            case "Cafe":
                fragment = new CafeFragment();
                break;
        }
        return fragment;
    }
}
