package com.example.advancedsoftwareengineering.ui.UserHandler;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.ITSupportService;
import com.example.advancedsoftwareengineering.ITworker;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.Sysadmin;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentUserHandlerBinding;
import com.example.advancedsoftwareengineering.ui.crediting.CreditingViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserHandlerFragment extends Fragment {
    private FragmentUserHandlerBinding binding;

    EditText username,nationalID,password,name,confirmPassword;
    Button addUser, removeUser;
    private CustomAdapter<User> adapter;
    ListView userListView;
    private List<CustomAdapter.ListItem<User>> users = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserHandlerViewModel userHandlerViewModel =
                new ViewModelProvider(this).get(UserHandlerViewModel.class);

        binding = FragmentUserHandlerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        Sysadmin admin = (Sysadmin) intent.getSerializableExtra("Actor");
        assert admin != null;

        name = binding.userhandelerNameTextbox;
        username = binding.userhandelerUsernameTextbox;
        nationalID = binding.userhandelerNationalIDTextbox;
        password = binding.userhandelerPasswordTextbox;
        confirmPassword = binding.userhandelerConfirmpasswordTextbox;
        userListView = binding.userhandelerUsersListview;
        addUser = binding.userhandlerAdduserButton;  // Initialize addUser button
        removeUser = binding.userhandlerRemoveuserButton;  // Initialize removeUser button

        userInitliazing();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("") && !username.getText().toString().equals("") &&
                        !nationalID.getText().toString().equals("") && !password.getText().toString().equals("") && !confirmPassword.getText().toString().equals("")
                        && password.getText().toString().equals(confirmPassword.getText().toString())) {

                    admin.createUserAccount(name.getText().toString(), username.getText().toString()
                            , nationalID.getText().toString(), password.getText().toString());
                    userInitliazing();
                    Log.d("User Handler", "Addmin: " +name.getText().toString()+" "+ username.getText().toString() +" "+nationalID.getText().toString()+" "+ password.getText().toString());
                } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(getContext(), "Confirm password incorrect", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please make sure to enter all the data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<User> clickedItem =
                        (CustomAdapter.ListItem<User>) parent.getItemAtPosition(position);

                removeUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("User Handler", "Removing: " + clickedItem.getItem().toString());
                        admin.removeUserAccount(clickedItem.getItem());
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
