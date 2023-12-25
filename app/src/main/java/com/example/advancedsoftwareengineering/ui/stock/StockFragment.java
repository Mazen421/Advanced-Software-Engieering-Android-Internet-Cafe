package com.example.advancedsoftwareengineering.ui.stock;

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
import com.example.advancedsoftwareengineering.CafeItemService;
import com.example.advancedsoftwareengineering.CustomAdapter;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.databinding.FragmentStockBinding;
import java.util.ArrayList;
import java.util.List;

public class StockFragment extends Fragment {


    private FragmentStockBinding binding;


    EditText nameText, typeText, stockText, priceText;
    Button changeButton;
    private CustomAdapter<CafeItemService> adapter;
    ListView stockList;
    private List<CustomAdapter.ListItem<CafeItemService>> stockItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStockBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Populate stockItems with initial data
        for (CafeItemService service : SERVER.cafeServices) {
            CustomAdapter.ListItem<CafeItemService> item = new CustomAdapter.ListItem<>(service, service.getServiceImage(), String.valueOf(service.getCount()));
            stockItems.add(item);
        }

        adapter = new CustomAdapter<>(getContext(), stockItems);

        nameText = binding.stockNameTextbox;
        typeText = binding.stockTypeTextbox;
        stockText = binding.stockInstockTextbox;
        priceText = binding.stockPriceTextbox;
        changeButton = binding.stockChangeButton;
        stockList = binding.stockStocklistListview;

        stockList.setAdapter(adapter);

        stockList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter.ListItem<CafeItemService> clickedItem = (CustomAdapter.ListItem<CafeItemService>) parent.getItemAtPosition(position);

                nameText.setText(clickedItem.getItem().getServiceName());
                typeText.setText(clickedItem.getItem().getItemType());
                stockText.setText(String.valueOf(clickedItem.getItem().getCount()));
                priceText.setText(String.valueOf(clickedItem.getItem().getPrice()));


                changeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the updated values from the UI elements
                        String newName = nameText.getText().toString();
                        String newType = typeText.getText().toString();
                        int newStock = Integer.parseInt(stockText.getText().toString());
                        double newPrice = Double.parseDouble(priceText.getText().toString());

                        // Update the corresponding CafeItemService in the SERVER.cafeServices list
                        for (CafeItemService service : SERVER.cafeServices) {
                            if (service.getServiceName().equals(clickedItem.getItem().getServiceName())) {
                                service.setServiceName(newName);
                                service.setItemType(newType);
                                service.setCount(newStock);
                                service.setPrice(newPrice);
                                break;
                            }
                        }

                        // Update the corresponding item in the adapter's data
                        clickedItem.getItem().setServiceName(newName);
                        clickedItem.getItem().setItemType(newType);
                        clickedItem.getItem().setCount(newStock);
                        clickedItem.getItem().setPrice(newPrice);
                        clickedItem.setText(String.valueOf(newStock));

                        // Notify the adapter that the data for this item has changed
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        return root;
    }
}
