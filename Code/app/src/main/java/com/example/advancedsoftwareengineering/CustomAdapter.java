package com.example.advancedsoftwareengineering;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter<T> extends ArrayAdapter<CustomAdapter.ListItem<T>> {

    public CustomAdapter(Context context, List<ListItem<T>> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem<T> item = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cafe_stock, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.horizontal_icon_item_list_textview);
            viewHolder.iconImageView = convertView.findViewById(R.id.horizontal_icon);
            viewHolder.countTextView = convertView.findViewById(R.id.count);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bindDataToView(viewHolder, item);

        return convertView;
    }

    private void bindDataToView(ViewHolder viewHolder, ListItem<T> item) {
        viewHolder.textView.setText(item.getItem().toString());
        viewHolder.iconImageView.setImageBitmap(item.getIconResourceId());
        viewHolder.countTextView.setText(item.getText()); // assuming getText() returns count as String
        // You can perform additional operations on viewHolder.arrowImageView if needed
    }

    private static class ViewHolder {
        TextView textView;
        ImageView iconImageView;
        TextView countTextView;
    }

    public static class ListItem<T> {
        private T object;
        private Bitmap bitmap;
        private String text;

        public ListItem(T object, Bitmap bitmap, String text) {
            this.object = object;
            this.bitmap = bitmap;
            this.text = text;
        }

        public T getItem() {
            return object;
        }

        public Bitmap getIconResourceId() {
            return bitmap;
        }

        public void setItem(T object) {
            this.object = object;
        }

        public void setIconResourceId(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
