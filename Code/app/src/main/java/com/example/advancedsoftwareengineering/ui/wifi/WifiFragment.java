package com.example.advancedsoftwareengineering.ui.wifi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.advancedsoftwareengineering.WIFIpassword;
import com.example.advancedsoftwareengineering.databinding.FragmentWifiBinding;

public class WifiFragment extends Fragment {

    private FragmentWifiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WifiViewModel wifiViewModel =
                new ViewModelProvider(this).get(WifiViewModel.class);

        binding = FragmentWifiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        TextView pass24 = binding.wifiPass24Textview,
                pass5 = binding.wifiPass5Textview;
        Button getPassword = binding.wifiGetpasswordButton;

        getPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WIFIpassword.getPassword() != null) {
                    pass24.setText(WIFIpassword.getPassword());
                }
                if(WIFIpassword.getPassword() != null) {
                    pass5.setText(WIFIpassword.getPassword());
                }

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}