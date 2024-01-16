package com.example.advancedsoftwareengineering.ui.credithandler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.Sysadmin;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentCreditHandlerBinding;

import java.util.ArrayList;
import java.util.List;


public class CreditHandlerFragment extends Fragment {


    private CustomAdapter<User> adapter;
    ListView userListView;
    private List<CustomAdapter.ListItem<User>> users = new ArrayList<>();

    EditText addCreditTextBox, removeCreditTextBox;
    Button changeCreditButton;
    private FragmentCreditHandlerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreditHandlerViewModel creditHandlerViewModel =
                new ViewModelProvider(this).get(CreditHandlerViewModel.class);

        binding = FragmentCreditHandlerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        Sysadmin admin = (Sysadmin) intent.getSerializableExtra("Actor");
        assert admin != null;

        userListView = binding.credithandlerUsersListview;
        addCreditTextBox = binding.credithandlerAddcreditTextbox;
        removeCreditTextBox = binding.credithandlerRemovecreditTextbox;
        changeCreditButton = binding.credithandlerChangecreditButton;


        userInitliazing();

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<User> clickedItem =
                        (CustomAdapter.ListItem<User>) parent.getItemAtPosition(position);

                changeCreditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!addCreditTextBox.getText().toString().equals("")){
                            admin.addCreditToUser(clickedItem.getItem(), Integer.parseInt(addCreditTextBox.getText().toString()));
                        }
                        if(!removeCreditTextBox.getText().toString().equals("")){
                            admin.removeCreditFromUser(clickedItem.getItem(), Integer.parseInt(removeCreditTextBox.getText().toString()));
                        }
                        userInitliazing();
                    }
                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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