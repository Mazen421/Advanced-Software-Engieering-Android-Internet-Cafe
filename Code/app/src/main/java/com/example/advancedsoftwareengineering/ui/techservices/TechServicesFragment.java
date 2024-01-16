package com.example.advancedsoftwareengineering.ui.techservices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentTechsupportBinding;

import java.util.Objects;

public class TechServicesFragment extends Fragment {

    private Button computer,keyboard,mouse,screen,headphones,microphone,program;
private FragmentTechsupportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        TechServicesViewModel techServicesViewModel =
                new ViewModelProvider(this).get(TechServicesViewModel.class);

    binding = FragmentTechsupportBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        computer = binding.techsupportComputerButton;
        keyboard = binding.techsupportKeyboardButton;
        mouse = binding.techsupportMouseButton;
        screen = binding.techsupportScreenButton;
        headphones = binding.techsupportHeadphonesButton;
        microphone = binding.techsupportMicrophoneButton;
        program = binding.techsupportProgramButton;
        Intent intent = requireActivity().getIntent();
        User user = (User) intent.getSerializableExtra("Actor");
        assert user != null;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a computer issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a computer issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();
            }
        });
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a keyboard issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a keyboard issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();
            }
        });
        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a mouse issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ////user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a mouse issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a screen issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a screen issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });
        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a headphone issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a headphones issue!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();


            }
        });
        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a microphone issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a microphone issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });
        program.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to call support for a program issue?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No
                                // Add your code to handle the negative response
                                Toast.makeText(getContext(), "You clicked No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //user.requestService(SERVER.PCservices.get(0));
                                Toast.makeText(getContext(), "You called support for a program issue!", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

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