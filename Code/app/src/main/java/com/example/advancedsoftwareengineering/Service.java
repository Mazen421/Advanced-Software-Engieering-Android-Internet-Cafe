package com.example.advancedsoftwareengineering;

//import java.awt.Image;  // Assuming you are using java.awt.Image for simplicity //I am not so i am using Bitmaps instead -Mazen421
import android.graphics.Bitmap;
public abstract class Service {
    private String serviceName;
    private boolean isRecurring;  // Indicates whether the service is ongoing (recurring) or one-time
    private double price;  // Combined price for both one-time and recurring services
    private Bitmap serviceImage;  // Picture for the service // Changes into bitmap to work in android -Mazen

    public Service(String serviceName, boolean isRecurring, double price, Bitmap serviceImage) {
        this.serviceName = serviceName;
        this.isRecurring = isRecurring;
        this.price = price;
        this.serviceImage = serviceImage;
    }

    // Getters and setters for the attributes
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bitmap getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(Bitmap serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String toString() {
        return "Service Name: " + serviceName + "\nRecurring: " + isRecurring + "\nPrice: " + price + "\n";
    }
}
