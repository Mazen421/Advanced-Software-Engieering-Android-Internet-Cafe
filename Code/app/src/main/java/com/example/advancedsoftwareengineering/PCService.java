package com.example.advancedsoftwareengineering;

import android.graphics.Bitmap;



public class PCService extends Service {

    Machine machine;

    public PCService(String serviceName, double price, Bitmap serviceImage) {
        super(serviceName, true, price, serviceImage);
        machine = new Machine("Playstation" , serviceName );
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Machine getMachine() {
        return machine;
    }

    //name the machine
    public void setMachineName(String name) {
        machine.setName(name);
    }
}