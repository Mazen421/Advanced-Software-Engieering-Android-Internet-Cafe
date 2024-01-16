package com.example.advancedsoftwareengineering.ui.cafehandleradder;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.advancedsoftwareengineering.CafeItemService;
import com.example.advancedsoftwareengineering.CafeWorker;
import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.databinding.FragmentCafeServiceHandlerBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CafeServiceHandlerFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_PERMISSION_CODE = 123; // Choose any unique code
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission granted, proceed with image selection logic
                    setupImageSelection();
                } else {
                    // Permission denied, handle accordingly (e.g., show a message to the user)
                }
            });

    TextView nameText, typeText, priceText;
    CafeWorker cafeWorker;
    ImageView imageView;
    Bitmap image;
    Button addButton, removeButton;
    private FragmentCafeServiceHandlerBinding binding;
    private CustomAdapter<CafeItemService> adapter;
    CafeItemService compareItem;
    ListView stockList;
    private List<CustomAdapter.ListItem<CafeItemService>> stockItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CafeServiceHandlerViewModel cafeServiceHandlerViewModel =
                new ViewModelProvider(this).get(CafeServiceHandlerViewModel.class);

        binding = FragmentCafeServiceHandlerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Intent intent = getActivity().getIntent();
        cafeWorker =  (CafeWorker) intent.getSerializableExtra("Actor");
        assert cafeWorker != null;

        nameText = binding.cafeserviceNameTextbox;
        typeText = binding.cafeserviceTypeTextbox;
        priceText = binding.cafeservicePriceTextbox;
        imageView = binding.cafeserviceImageImageview;
        stockList = binding.cafeserviceItemsListview;
        addButton = binding.cafeserviceAddButton;
        removeButton = binding.cafeserviceRemoveButton;


        cafeItemsInitliazing();





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            // Request the permission using the modern approach
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            // Permission already granted, proceed with image selection logic
            setupImageSelection();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareItem = new CafeItemService(nameText.getText().toString(), Double.parseDouble(priceText.getText().toString())
                        ,image,typeText.getText().toString());
                if(!SERVER.cafeServices.contains(compareItem)) {
                    cafeWorker.addCafeService(nameText.getText().toString(), Double.parseDouble(priceText.getText().toString())
                            , image, typeText.getText().toString());
                }
                else{
                    Toast.makeText(getContext(),"Item already exists", Toast.LENGTH_SHORT).show();
                }

                cafeItemsInitliazing();
            }
        });

        stockList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<CafeItemService> clickedItem =
                        (CustomAdapter.ListItem<CafeItemService>) parent.getItemAtPosition(position);
                nameText.setText(clickedItem.getItem().getServiceName());
                typeText.setText(clickedItem.getItem().getItemType());
                priceText.setText(String.valueOf(clickedItem.getItem().getPrice()));
                imageView.setImageBitmap(clickedItem.getItem().getServiceImage());

                removeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        cafeWorker.removeCafeService(clickedItem.getItem());
                        cafeItemsInitliazing();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            Uri imageUri = data.getData();

            // Convert the URI to a Bitmap
            Bitmap bitmap = getBitmapFromUri(imageUri);
            image = getBitmapFromUri(imageUri);

            // Display the selected image in the ImageView
            imageView.setImageBitmap(bitmap);
        }
    }

    // Helper method to convert URI to Bitmap
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, proceed with image selection logic
            setupImageSelection();
        } else {
            // Permission denied, handle accordingly (e.g., show a message to the user)
        }
    }

    private void setupImageSelection() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    private void cafeItemsInitliazing(){
        stockItems.clear();
        for (CafeItemService service : SERVER.cafeServices) {
            CustomAdapter.ListItem<CafeItemService> item = new CustomAdapter.ListItem<>(service, service.getServiceImage(), String.valueOf(service.getCount()));
            stockItems.add(item);
        }
        adapter = new CustomAdapter<>(getContext(), stockItems);
        stockList.setAdapter(adapter);
    }

}
