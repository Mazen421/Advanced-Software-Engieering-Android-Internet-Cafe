package com.example.advancedsoftwareengineering;

import android.graphics.Bitmap;

public class CafeItemService extends Service {
    private String itemType;  // e.g., Snack, Drink
    private int count = 10;

    public CafeItemService(String serviceName, double price, Bitmap serviceImage, String itemType) {
        super(serviceName, false, price, serviceImage);
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    public void stockUp(int count) {
        this.count += count;
    }

    public void decrementCount() {
        this.count--;
    }

    public boolean isAvailable() {
        return this.count > 0;
    }

    public String toString() {
        return super.toString() + "\nItem Type: " + itemType + "\nCount: " + count;
    }

}
