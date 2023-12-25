package com.example.advancedsoftwareengineering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



import android.graphics.Bitmap;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private List<HorizontalListItem> dataList;

    public HorizontalAdapter(List<HorizontalListItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind your data to the views in each item
        HorizontalListItem item = dataList.get(position);
        // Set data to views in the ViewHolder

        // Example: Set text to TextView
        holder.textView.setText(item.getText());

        // Example: Set icon to ImageView
        holder.iconImageView.setImageBitmap(item.getIconBitmap());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Add your views for each item here
        TextView textView;
        ImageView iconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your views
            textView = itemView.findViewById(R.id.horizontal_icon_item_list_textview);
            iconImageView = itemView.findViewById(R.id.horizontal_icon);
        }
    }

    public static class HorizontalListItem {
        private String text;
        private Bitmap iconBitmap;  // Use Bitmap instead of resource ID

        public HorizontalListItem(String text, Bitmap iconBitmap) {
            this.text = text;
            this.iconBitmap = iconBitmap;
        }

        public String getText() {
            return text;
        }

        public Bitmap getIconBitmap() {
            return iconBitmap;
        }

        // You can add other methods or properties specific to HorizontalListItem
    }
}
