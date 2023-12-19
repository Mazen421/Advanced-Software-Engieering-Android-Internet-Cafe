package com.example.advancedsoftwareengineering;

import java.awt.Image;  // Assuming you are using java.awt.Image for simplicity

public abstract class Service {
    private String serviceName;
    private boolean isRecurring;  // Indicates whether the service is ongoing (recurring) or one-time
    private double price;  // Combined price for both one-time and recurring services
    private Image serviceImage;  // Picture for the service

    public Service(String serviceName, boolean isRecurring, double price, Image serviceImage) {
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

    public Image getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(Image serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String toString() {
        return "Service Name: " + serviceName + "\nRecurring: " + isRecurring + "\nPrice: " + price + "\n";
    }
}
