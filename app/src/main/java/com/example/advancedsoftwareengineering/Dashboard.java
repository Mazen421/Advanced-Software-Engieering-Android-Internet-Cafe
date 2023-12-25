package com.example.advancedsoftwareengineering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.advancedsoftwareengineering.databinding.ActivityDashboardBinding;


import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ScrollView customDrawer;
    private ActivityDashboardBinding binding;

    private Actor actor;

    TextView nameTextView, underlineTextView;

    boolean toggle = false;

    int selectedPosition;

    ListView listView;
    List<ListItem> itemList;

    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        Button button = binding.appBarDashboard.button11;
        drawerLayout = binding.drawerLayout;
        customDrawer = binding.customDrawer;

        listView = binding.navListView;

        // Inflate the header layout and add it to the ListView
        View headerView = (View)getLayoutInflater().inflate(R.layout.nav_header_dashboard, null);
        listView.addHeaderView(headerView);


        initializeListView();


        // Create the adapter to convert the array to views
        adapter = new CustomAdapter(this, itemList);

        // Attach the adapter to a ListView
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Execute the task based on the clicked item
                ListItem clickedItem = (ListItem) parent.getItemAtPosition(position); // Subtract 1 for the header
                    if(clickedItem != null && clickedItem.getText() != null && clickedItem.getText() == "Services"){
                        Log.d("OnItemClickListener", "Expand menu");
                        toggleServices(position);
                        // Toggle the arrow direction
                        clickedItem.setArrowDown(!clickedItem.isArrowDown());
                        // Notify the adapter that the data set changed
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        if(clickedItem != null && FrontEndHelper.fragmentDecider(clickedItem.getText()) != null){
                            Log.d("OnItemClickListener", "Loading fragment "+clickedItem.getText());
                            loadFragment(FrontEndHelper.fragmentDecider(clickedItem.getText()));
                            toggleCustomDrawer();
                        }
                    }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCustomDrawer();
            }
        });

    }

    public void toggleCustomDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(customDrawer, true);
        }
    }

    public void toggleServices(int pos){
        if(toggle) {
            for (int i = pos + 1; i < pos + 4; i++) {
                itemList.remove(pos);
            }
            toggle = false;
        }
        else{
            itemList.add(pos,new ListItem("Tech Support", R.drawable.indented_gear_icon_freepik, false, true));
            itemList.add(pos + 1,new ListItem("Wi-Fi", R.drawable.indented_wifi_icon_freepik, false, true));
            itemList.add(pos + 2,new ListItem("Reserve a Device", R.drawable.indented_reserve_icon2, false, true));
            toggle = true;
        }
    }



    public class ListItem {
        private String text;
        private int iconResourceId;
        private boolean isArrowDown;
        private boolean isChild;


        public ListItem(String text, int iconResourceId, boolean isArrowDown, boolean isChild) {
            this.text = text;
            this.iconResourceId = iconResourceId;
            this.isArrowDown = isArrowDown;
            this.isChild = isChild;
        }

        public boolean isChild() {
            return isChild;
        }

        public void setChild(boolean child) {
            isChild = child;
        }

        public String getText() {
            return text;
        }


        public int getIconResourceId() {
            return iconResourceId;
        }

        public void setText(String text) {
            this.text = text;
            adapter.notifyDataSetChanged();
        }

        public void setIconResourceId(int iconResourceId) {
            this.iconResourceId = iconResourceId;
            adapter.notifyDataSetChanged();
        }

        public boolean isArrowDown() {
            return isArrowDown;
        }

        public void setArrowDown(boolean arrowDown) {
            isArrowDown = arrowDown;
        }
    }


    public class CustomAdapter extends ArrayAdapter<ListItem> {

        public CustomAdapter(Context context, List<ListItem> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            ListItem item = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

                // Animation applied only when a new view is created
                if (isChild()) {
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    convertView.startAnimation(animation);
                }
            }

            // Lookup view for data population
            TextView textView = convertView.findViewById(R.id.horizontal_icon_item_list_textview);
            ImageView iconImageView = convertView.findViewById(R.id.horizontal_icon);

            // Populate the data into the template view using the data object
            if (item.isChild) {
                textView.setTextSize(8);
            }
            textView.setText(item.getText());
            iconImageView.setImageResource(item.getIconResourceId());

            ImageView arrowImageView = convertView.findViewById(R.id.arrowIcon);
            if (arrowImageView != null) {
                if (position == selectedPosition) {
                    if (item.isArrowDown()) {
                        arrowImageView.setImageResource(R.drawable.ic_arrow_side);
                    } else {
                        arrowImageView.setImageResource(R.drawable.ic_arrow_down);
                    }
                }
            }

            // Return the completed view to render on screen
            return convertView;
        }
    }


    public void initializeListView(){
        String actorType;
        Intent intent = getIntent();
        itemList = new ArrayList<>();
        actor = (Actor) intent.getSerializableExtra("Actor");
        if(actor instanceof User){
            User user = (User)actor;
            actorType = "User";
            Log.d("initializeListView","actor is user");
            loadFragment(FrontEndHelper.fragmentDecider("Home"));
        } else if (actor instanceof ITworker) {
            ITworker it1 = (ITworker)actor;
            actorType = "ITworker";
            Log.d("initializeListView","actor is ItWorker");
            loadFragment(FrontEndHelper.fragmentDecider("IT Service"));
        } else if (actor instanceof CafeWorker) {
            CafeWorker cafe1 = (CafeWorker)actor;
            actorType = "CafeWorker";
            Log.d("initializeListView","actor is CafeWorker");
            loadFragment(FrontEndHelper.fragmentDecider("Stock"));
        } else if (actor instanceof Sysadmin) {
            Sysadmin admin1 = (Sysadmin) actor;
            actorType = "Sysadmin";
            Log.d("initializeListView","actor is Sysadmin");
            loadFragment(FrontEndHelper.fragmentDecider("Manage Users"));
        }
        else{
            actorType = "";
        }

        switch (actorType){
            case "User":
                itemList.add(new ListItem("Home", R.drawable.profile_icon_freepik, false, false));
                itemList.add(new ListItem("Cafe", R.drawable.coffee_cup_freepik, false, false));
                itemList.add(new ListItem("Services", R.drawable.customer_support_icon, true, false));
                selectedPosition = 2;
                itemList.add(new ListItem("Transaction History", R.drawable.transaction_history_icon, false, false));
                itemList.add(new ListItem("Time", R.drawable.time_money_icon, false, false));
                itemList.add(new ListItem("Contact and Crediting", R.drawable.contact_icon, false, false));

                break;
            case "ITworker":
                itemList.add(new ListItem("IT Service", R.drawable.technical_support, false, false));
                itemList.add(new ListItem("Machines", R.drawable.machines_icon, false, false));
                selectedPosition = 10;

                break;
            case "CafeWorker":
                itemList.add(new ListItem("Stock", R.drawable.stock_icon, false,false));
                itemList.add(new ListItem("Inventory", R.drawable.inventory_icon,false,false));
                selectedPosition = 10;
                break;
            case "Sysadmin":
                itemList.add(new ListItem("Manage Users", R.drawable.manage_user_icon, false, false));
                itemList.add(new ListItem("Blacklist", R.drawable.banned_icon, false, false));
                itemList.add(new ListItem("Users Credit", R.drawable.time_money_icon, false, false));
                selectedPosition = 10;
                break;
            default:
        }
    }


    private void loadFragment(Fragment fragment) {
        // Create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with the new one
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
        Log.d("loadFragment", "fragment loaded "+fragment.toString());
    }

}