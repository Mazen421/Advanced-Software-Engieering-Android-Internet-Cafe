package com.example.advancedsoftwareengineering.ui.userhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advancedsoftwareengineering.CafeItemService;
import com.example.advancedsoftwareengineering.HorizontalAdapter;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.User;
import com.example.advancedsoftwareengineering.databinding.FragmentUserhomeBinding;

import java.util.ArrayList;
import java.util.List;

public class UserHomeFragment extends Fragment {

private FragmentUserhomeBinding binding;
private TextView username,nationalID,currentCredit,currentDevice;

private RecyclerView recyclerView;
    public UserHomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserHomeViewModel userHomeViewModel = new ViewModelProvider(this).get(UserHomeViewModel.class);
        Intent intent = getActivity().getIntent();
        User actor= (User) intent.getSerializableExtra("Actor");
        assert actor != null;

        binding = FragmentUserhomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        username = binding.homeUsernameTextview;
        nationalID = binding.homeNationalIDTextview;
        currentCredit = binding.homeCreditsTextview;
        currentDevice = binding.homeCurrentcreditTextview;



        username.setText(actor.getUsername());
        nationalID.setText(actor.getNationalId().substring(0,3)+"XXXXXXXXX");
        currentCredit.setText( String.valueOf(actor.getCredits()));
        if(actor.getAssignedMachine() != null){
            currentDevice.setText("Machine Name: " +actor.getAssignedMachine().getName()
                    + "\n Machine Type: " + actor.getAssignedMachine().getType()
                    + "\nUsage Time: " + actor.getAssignedMachine().getUsageTime() );
        }
        else{
            currentDevice.setText("You have not reserved a machine yet");
        }

        recyclerView = binding.homePopularshopRecycleview;

        List<HorizontalAdapter.HorizontalListItem> popularItems = new ArrayList<>();

        List<CafeItemService> cafeItemServiceList = SERVER.cafeServices;

        for(CafeItemService c : cafeItemServiceList){
            Log.d("populateAdapter",c.getServiceName());
            popularItems.add(new HorizontalAdapter.HorizontalListItem(c.getServiceName(),c.getServiceImage()));
        }

        recyclerView.setAdapter(new HorizontalAdapter(popularItems));




        for(CafeItemService c : SERVER.cafeServices){
            System.out.println(c.getServiceName() +"\n"+c.getPrice()+"\n"+c.getItemType()+"\n"+c.getCount());
        }



        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}