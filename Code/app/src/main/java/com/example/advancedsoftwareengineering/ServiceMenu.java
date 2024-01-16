package com.example.advancedsoftwareengineering;

import java.util.ArrayList;
import java.util.List;

//DEPRECATED, REPLACED WITH SERVER CLASS

public class ServiceMenu {
    private List<Service> services;

    //menu name
    private String menuName;

    public ServiceMenu(String menuName) {
        this.services = new ArrayList<>();
        this.menuName = menuName;
    }

    // Add a service to the menu
    public void addService(Service service) {
        services.add(service);
    }

    public List<Service> getServices() {
        return services;
    }

    public void removeService(Service service) {
        services.remove(service);
    }


    // Get a specific service by name
    public Service getServiceByName(String serviceName) {
        for (Service service : services) {
            if (service.getServiceName().equals(serviceName)) {
                return service;
            }
        }
        return null;
    }

    public Service getServiceByIndex(int index) {
        return services.get(index);
    }

    public int getServiceCount() {
        return services.size();
    }

    public void printServices() {
        for (Service service : services) {
            System.out.println(service.getServiceName());
        }
    }

    public void printServicesWithPrice() {
        for (Service service : services) {
            System.out.println(service.getServiceName() + " - " + service.getPrice());
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String toString() {
        String str = "";
        for (Service service : services) {
            str += service.toString() + "\n";
        }
        return str;
    }


}
