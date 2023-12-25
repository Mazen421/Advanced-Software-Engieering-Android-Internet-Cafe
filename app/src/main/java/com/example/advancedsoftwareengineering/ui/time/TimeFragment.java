package com.example.advancedsoftwareengineering.ui.time;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentTimeBinding;

import java.util.Objects;


public class TimeFragment extends Fragment {
    private FragmentTimeBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TimeViewModel timeViewModel =
                new ViewModelProvider(this).get(TimeViewModel.class);

        binding = FragmentTimeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button endSessionButton = binding.timeEndsessionButton
                , pauseSessionButton = binding.timePausetimeButton;

        TextView currentMachineTextView = binding.timeMachineanmeTextview
                , remainingTimeTextView = binding.timeRemainingtimeTextview
                , machinePriceTextView = binding.timePriceTextview
                , machineInfoTextView =  binding.timeMachineinfoTextview
                , machineTypeTextView = binding.timeMachientypeTextview;

        Intent intent = requireActivity().getIntent();
        User user= (User) intent.getSerializableExtra("Actor");
        assert user != null;


        if(user.getAssignedMachine() != null){
            machineInfoTextView.setText("Machine Name: " +user.getAssignedMachine().getName()
                    + "\n Machine Type: " + user.getAssignedMachine().getType()
                    + "\nUsage Time: " + user.getAssignedMachine().getUsageTime() );
            if(user.getAssignedMachine().isPC()){
                machineTypeTextView.setText("PC Info:");
            }
            else{
                machineTypeTextView.setText("PS Info:");
            }
            currentMachineTextView.setText(user.getAssignedMachine().getName());
            //remainingTimeTextView.setText(); we want to get price/remaining credit
            //machinePriceTextView.setText(user.getAssignedMachine().getPrice());
        }

        endSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user.requestService();
            }
        });

        pauseSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user.requestService()
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