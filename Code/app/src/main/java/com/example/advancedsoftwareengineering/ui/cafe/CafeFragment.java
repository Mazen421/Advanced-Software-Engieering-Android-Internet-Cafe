package com.example.advancedsoftwareengineering.ui.cafe;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.advancedsoftwareengineering.CafeItemService;
import com.example.advancedsoftwareengineering.HorizontalAdapter;
import com.example.advancedsoftwareengineering.R;
import com.example.advancedsoftwareengineering.SERVER;
import com.example.advancedsoftwareengineering.databinding.FragmentCafeBinding;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CafeFragment extends Fragment {

    private TableLayout drinksTableLayout, foodTableLayout;
private FragmentCafeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        CafeViewModel cafeViewModel =
                new ViewModelProvider(this).get(CafeViewModel.class);

    binding = FragmentCafeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        drinksTableLayout = binding.cafeDrinksTablelayout;
        foodTableLayout = binding.cafeFoodTablelayout;
        List<HorizontalAdapter.HorizontalListItem> drinksList = new ArrayList<>();
        List<HorizontalAdapter.HorizontalListItem> foodList = new ArrayList<>();

        List<CafeItemService> cafeItemServiceList = SERVER.cafeServices;

        for (CafeItemService c : cafeItemServiceList){
            if(c.getItemType().equals("Drink")){
                drinksList.add(new HorizontalAdapter.HorizontalListItem(c.getServiceName(),c.getServiceImage()));
            }
            else{
                foodList.add(new HorizontalAdapter.HorizontalListItem(c.getServiceName(),c.getServiceImage()));
            }
        }

        int counter = (drinksList.size()+1)/2;
        for(int i=0;i<counter ;i++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int j = 0; j < 2; j++) {
                Log.d("drinkcounter", "index: " + (i+j) + " item :" + drinksList.get(i+j).getText());

                View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.horizontal_list_item, null);

                ImageView itemImageView = itemView.findViewById(R.id.horizontal_icon);
                TextView itemTextView = itemView.findViewById(R.id.horizontal_icon_item_list_textview);

                itemImageView.setImageBitmap(drinksList.get(i+j).getIconBitmap());
                itemTextView.setText(drinksList.get(i+j).getText());

                tableRow.addView(itemView);
                if(j==1){
                    i = i+j;
                }

            }

            drinksTableLayout.addView(tableRow);
            if (i + 2 < counter) {
                View separator = new View(getActivity());
                separator.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        1)); // 1 pixel height
                separator.setBackgroundColor(Color.parseColor("#B4B4B4")); // Replace with your desired color
                drinksTableLayout.addView(separator);
            }
        }

        counter = (foodList.size()+1)/2;
        for(int i=0;i<counter ;i++) {
            TableRow tableRow = new TableRow(getActivity());
            for (int j = 0; j < 2; j++) {
                Log.d("foodcounter", "index: " + (i+j)+ " item :" + foodList.get(i+j).getText());

                View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.horizontal_list_item, null);

                ImageView itemImageView = itemView.findViewById(R.id.horizontal_icon);
                TextView itemTextView = itemView.findViewById(R.id.horizontal_icon_item_list_textview);

                itemImageView.setImageBitmap(foodList.get(i+j).getIconBitmap());
                itemTextView.setText(foodList.get(i+j).getText());

                tableRow.addView(itemView);
                if(j==1){
                    i = i+j;
                }

            }

            foodTableLayout.addView(tableRow);
            if (i + 2 < counter) {
                View separator = new View(getActivity());
                separator.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        1)); // 1 pixel height
                separator.setBackgroundColor(Color.parseColor("#B4B4B4")); // Replace with your desired color
                foodTableLayout.addView(separator);
            }
        }
        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}