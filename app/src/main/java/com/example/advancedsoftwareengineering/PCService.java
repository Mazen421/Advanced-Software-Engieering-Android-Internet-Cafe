package com.example.advancedsoftwareengineering;

import java.awt.*;

public class PCService extends Service {

    Machine machine;

    public PCService(String serviceName, double price, Image serviceImage) {
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