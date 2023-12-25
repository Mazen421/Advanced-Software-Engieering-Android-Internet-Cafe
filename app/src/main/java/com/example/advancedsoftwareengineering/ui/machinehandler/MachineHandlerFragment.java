package com.example.advancedsoftwareengineering.ui.machinehandler;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.ITSupportService;
import com.example.advancedsoftwareengineering.ITworker;
import com.example.advancedsoftwareengineering.PCService;
import com.example.advancedsoftwareengineering.PlaystationService;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.databinding.FragmentMachineHandlerBinding;
import com.example.advancedsoftwareengineering.ui.ItHandler.ItHandlerViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MachineHandlerFragment extends Fragment {


    TextView nameText, priceText;
    ITworker iTworker;
    Button addButton, removeButton;
    Switch typeSwitch;
    private FragmentMachineHandlerBinding binding;
    private CustomAdapter<PCService> pcAdapter;
    private CustomAdapter<PlaystationService> playstationAdapter;
    PCService pcCompareItem;
    PlaystationService playstationCompareItem;
    ListView pcList, playstationList;
    private List<CustomAdapter.ListItem<PCService>> pcItems = new ArrayList<>();
    private List<CustomAdapter.ListItem<PlaystationService>> playstationItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MachineHandlerViewModel machineHandlerViewModel =
                new ViewModelProvider(this).get(MachineHandlerViewModel.class);

        binding = FragmentMachineHandlerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        iTworker =  (ITworker) intent.getSerializableExtra("Actor");
        assert iTworker != null;

        nameText = binding.machinehandlerNameTextbox;
        typeSwitch = binding.machinehandlerTypeSwitch;
        priceText = binding.machinehandlerPriceTextbox;
        pcList = binding.machinehandlerPcItemsListview;
        playstationList = binding.machinehandlerPlaysationItemsListview;
        addButton = binding.machinehandlerAddButton;
        removeButton = binding.machinehandlerRemoveButton;



        machinePcInitliazing();
        machinePlaystationInitliazing();







        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeSwitch.isChecked()){
                    playstationCompareItem = new PlaystationService(nameText.getText().toString(), Double.parseDouble(priceText.getText().toString()),null);

                    if(!SERVER.PlaystationServices.contains(playstationCompareItem)) {
                        iTworker.addMachineService(nameText.getText().toString(),"Playstation", Double.parseDouble(priceText.getText().toString()));

                    }
                    else{
                        Toast.makeText(getContext(),"Item already exists", Toast.LENGTH_SHORT).show();
                    }

                    machinePlaystationInitliazing();

                }
                else{
                    pcCompareItem = new PCService(nameText.getText().toString(), Double.parseDouble(priceText.getText().toString()),null);

                    if(!SERVER.PCservices.contains(pcCompareItem)) {
                        iTworker.addMachineService(nameText.getText().toString(),"PC", Double.parseDouble(priceText.getText().toString()));

                    }
                    else{
                        Toast.makeText(getContext(),"Item already exists", Toast.LENGTH_SHORT).show();
                    }

                    machinePcInitliazing();
                }
            }
        });

        playstationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<PlaystationService> clickedItem =
                        (CustomAdapter.ListItem<PlaystationService>) parent.getItemAtPosition(position);

                nameText.setText(clickedItem.getItem().getServiceName());
                priceText.setText(String.valueOf(clickedItem.getItem().getPrice()));
                typeSwitch.setChecked(true);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iTworker.removeMachineService(clickedItem.getItem().getServiceName(), clickedItem.getItem().getMachine().getType());
                        machinePlaystationInitliazing();
                    }
                });
            }
        });

        pcList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<PCService> clickedItem =
                        (CustomAdapter.ListItem<PCService>) parent.getItemAtPosition(position);
                nameText.setText(clickedItem.getItem().getServiceName());
                priceText.setText(String.valueOf(clickedItem.getItem().getPrice()));
                typeSwitch.setChecked(false);

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        iTworker.removeMachineService(clickedItem.getItem().getServiceName(), clickedItem.getItem().getMachine().getType());
                        machinePcInitliazing();
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

    private void machinePcInitliazing(){
        pcItems.clear();
        for (PCService service : SERVER.PCservices) {
            CustomAdapter.ListItem<PCService> item = new CustomAdapter.ListItem<>(service, service.getServiceImage(), "");
            pcItems.add(item);
        }
        pcAdapter = new CustomAdapter<>(getContext(), pcItems);
        pcList.setAdapter(pcAdapter);
    }

    private void machinePlaystationInitliazing(){
        playstationItems.clear();
        for (PlaystationService service : SERVER.PlaystationServices) {
            CustomAdapter.ListItem<PlaystationService> item = new CustomAdapter.ListItem<>(service, service.getServiceImage(), "");
            playstationItems.add(item);
        }
        playstationAdapter = new CustomAdapter<>(getContext(), playstationItems);
        playstationList.setAdapter(playstationAdapter);
    }

}
