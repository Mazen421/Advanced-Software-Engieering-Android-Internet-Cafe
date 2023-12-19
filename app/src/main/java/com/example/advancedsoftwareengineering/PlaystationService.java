package com.example.advancedsoftwareengineering;

import java.awt.*;

public class PlaystationService extends Service {

    Machine machine;

    public PlaystationService(String serviceName, double price, Image serviceImage) {
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