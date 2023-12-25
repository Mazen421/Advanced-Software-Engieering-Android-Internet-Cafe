package com.example.advancedsoftwareengineering.ui.UserBlacklist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.Sysadmin;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentUserBlacklistBinding;

import java.util.ArrayList;
import java.util.List;


public class UserBlacklistFragment extends Fragment {

    private FragmentUserBlacklistBinding binding;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch blacklistSwitch;
    private CustomAdapter<User> adapter;
    ListView userListView;
    private List<CustomAdapter.ListItem<User>> users = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserBlacklistViewModel userBlacklistViewModel =
                new ViewModelProvider(this).get(UserBlacklistViewModel.class);

        binding = FragmentUserBlacklistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        Sysadmin admin = (Sysadmin) intent.getSerializableExtra("Actor");
        assert admin != null;

        userListView = binding.userblacklistUsersListview;
        blacklistSwitch = binding.userblacklistBlacklistedSwitch;



        userInitliazing();

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<User> clickedItem =
                        (CustomAdapter.ListItem<User>) parent.getItemAtPosition(position);
                if (clickedItem.getItem().isBlacklisted()) {
                    blacklistSwitch.setChecked(true);
                    Log.d("Blacklisting", "User is blackListed");
                } else {
                    blacklistSwitch.setChecked(false);
                    Log.d("Blacklisting", "User is not blacklisted");
                }

                blacklistSwitch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(blacklistSwitch.isChecked()){
                            admin.toggleBlacklist(clickedItem.getItem());
                            Log.d("Blacklisting", "Blacklisting:" + clickedItem.getItem().getName());
                            userInitliazing();
                        }
                        else{
                            admin.toggleBlacklist(clickedItem.getItem());
                            Log.d("Blacklisting", "Removing Blacklist: " + clickedItem.getItem().getName());
                            userInitliazing();
                        }
                    }
                });

            }
        });

        return root;

    }


    private void userInitliazing(){
        users.clear();
        for (User u : SERVER.users) {
            CustomAdapter.ListItem<User> item = new CustomAdapter.ListItem<>(u,null,"");
            users.add(item);
        }
        adapter = new CustomAdapter<>(getContext(), users);
        userListView.setAdapter(adapter);
    }
}