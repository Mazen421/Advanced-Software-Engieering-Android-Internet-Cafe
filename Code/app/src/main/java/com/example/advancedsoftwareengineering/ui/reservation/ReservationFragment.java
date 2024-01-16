package com.example.advancedsoftwareengineering.ui.reservation;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.advancedsoftwareengineering.databinding.FragmentReservationBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ReservationFragment extends Fragment {

    private FragmentReservationBinding binding;

    Spinner option1Spinner,option2Spinner,option3Spinner;
    ArrayAdapter<String> option1Adapter,option2Adapter,option3Adapter;
    List<String> option1List,option2List,option3List;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReservationViewModel reservationViewModel =
                new ViewModelProvider(this).get(ReservationViewModel.class);

        binding = FragmentReservationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RadioButton pcRadioButton, psRadioButton;
        TextView option1,option2,option3;


        option1 = binding.reservationOption1Textview;
        option2 = binding.reservationOption2Textview;
        option3 = binding.reservationOption3Textview;

        option1List = new ArrayList<>();
        option2List = new ArrayList<>();
        option3List = new ArrayList<>();

        option1Spinner = binding.reservationOption1Spinner;
        option2Spinner = binding.reservationOption2Spinner;
        option3Spinner = binding.reservationOption3Spinner;


        pcRadioButton = binding.reservationPCRadiobutton;
        psRadioButton = binding.reservationPSRadiobutton;

        spinnerInitliazer(false);

        pcRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Your code to handle the radio button state change
                if (isChecked) {
                    option1.setText("CPU");
                    option2.setText("GPU");
                    option3.setVisibility(View.VISIBLE);
                    option3.setText("Ram");

                    spinnerInitliazer(true);



                }
            }
        });

        psRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Your code to handle the radio button state change
                if (isChecked) {
                    option1.setText("Playstation Version");
                    option2.setText("Avalible Games");
                    option3.setVisibility(View.INVISIBLE);

                    spinnerInitliazer(false);



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

    public void spinnerInitliazer(Boolean bool){
        if(bool){
            option3Spinner.setVisibility(View.VISIBLE);

            option1List.clear();
            option2List.clear();
            option3List.clear();

            option1List.add("Core i7 10th gen");
            option1List.add("Core i7 11th gen");
            option1List.add("Core i7 12th gen");
            option1List.add("Core i5 10th gen");
            option1List.add("Core i5 11th gen");
            option1List.add("Core i5 12th gen");
            option1List.add("Core i3 10th gen");
            option1List.add("Core i3 9th gen");

            option2List.add("Nvidia RTX 3050");
            option2List.add("Nvidia RTX 3060");
            option2List.add("Nvidia RTX 3070");
            option2List.add("Nvidia RTX 3080");
            option2List.add("Nvidia RTX 4050");
            option2List.add("Nvidia RTX 4060");
            option2List.add("Nvidia RTX 4070");
            option2List.add("Nvidia RTX 4080");

            option3List.add("8 GB");
            option3List.add("16 GB");
            option3List.add("32 GB");

            option1Adapter = new ArrayAdapter<>(requireActivity(),android.R.layout.simple_spinner_item, option1List);
            option2Adapter = new ArrayAdapter<>(requireActivity(),android.R.layout.simple_spinner_item, option2List);
            option3Adapter = new ArrayAdapter<>(requireActivity(),android.R.layout.simple_spinner_item, option3List);
        }
        else{
            option1List.clear();
            option2List.clear();
            option3List.clear();

            option3Spinner.setVisibility(View.INVISIBLE);

            option1List.add("Playstation 3");
            option1List.add("Playstation 4");
            option1List.add("Playstation 5");

            option2List.add("Fifa 2023");
            option2List.add("Fifa 2022");
            option2List.add("Fifa 2021");
            option2List.add("Pes 2023");
            option2List.add("Pes 2022");
            option2List.add("Pes 2021");
            option2List.add("Tekken");
            option2List.add("Racing");

        }
        option1Spinner.setAdapter(option1Adapter);
        option2Spinner.setAdapter(option2Adapter);
        option3Spinner.setAdapter(option3Adapter);
    }
}