package com.example.advancedsoftwareengineering.ui.crediting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.databinding.FragmentCreditingBinding;

public class CreditingFragment extends Fragment {

    private FragmentCreditingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreditingViewModel creditingViewModel =
                new ViewModelProvider(this).get(CreditingViewModel.class);

        binding = FragmentCreditingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
/*
private Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ViewModel ViewModel =
                new ViewModelProvider(this).get(ServicesViewModel.class);

    binding = Binding.inflate(inflater, container, false);
    View root = binding.getRoot();


        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
 */