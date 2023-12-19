package com.example.advancedsoftwareengineering;

import java.awt.*;

public class ITSupportService extends Service {
    private String supportType; //If it's WIFI, handled automatically by the system
                                //Else, handled by the IT worker

    public ITSupportService(String serviceName, double price, Image serviceImage, String supportType) {
        super(serviceName, false, price, serviceImage);
        this.supportType = supportType;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.supportType = supportType;
    }


    public String toString() {
        return super.toString() + "\nSupport Type: " + supportType ;
    }


}