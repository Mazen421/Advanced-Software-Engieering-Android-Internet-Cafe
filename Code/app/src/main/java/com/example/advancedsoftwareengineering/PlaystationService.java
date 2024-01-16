package com.example.advancedsoftwareengineering;

import android.graphics.Bitmap;



public class PlaystationService extends Service {

    Machine machine;

    public PlaystationService(String serviceName, double price, Bitmap serviceImage) {
        super(serviceName, true, price, serviceImage);
        machine = new Machine("Playstation" ,serviceName);
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }


}