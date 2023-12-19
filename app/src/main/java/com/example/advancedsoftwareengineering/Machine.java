package com.example.advancedsoftwareengineering;

public class Machine {
    private String type;  // PC or Playstation
    private boolean isAvailable;
    private GlobalTime usageTime;
    private User assignedUser;

    private String name;

    public Machine(String type, String name) {
        this.type = type;
        this.isAvailable = true;
        this.usageTime = new GlobalTime();
        this.assignedUser = null;
    }

    // Mark the machine as available
    public void setAvailable() {
        isAvailable = true;
    }

    // Mark the machine as unavailable and assign a user
    public void setUnavailableAndAssignUser(User user) {
        isAvailable = false;
        assignedUser = user;
        this.usageTime = new GlobalTime();
    }

    // Mark the machine as available and unassign the user
    public void setAvailableAndUnassignUser() {
        isAvailable = true;
        assignedUser = null;
    }

    // Get the machine's usage time in string format
    public String getUsageTime() {
        return usageTime.getAgeFormatted();
    }

    // Get the machine's usage time
    public GlobalTime getUsageTimeObject() {
        return usageTime;
    }

    // Get the assigned user
    public User getAssignedUser() {
        return assignedUser;
    }

    // Get the machine's type
    public String getType() {
        return type;
    }



    // Check if the machine is available
    public boolean isAvailable() {
        return isAvailable;
    }

    // Check if the machine is available
    public boolean isOccupied() {
        return !isAvailable;
    }

    // Check if the machine is a PC
    public boolean isPC() {
        return type.equals("PC");
    }

    // Check if the machine is a Playstation
    public boolean isPlaystation() {
        return type.equals("Playstation");
    }

    //reset the machine
    public void reset() {
        isAvailable = true;
        assignedUser = null;
        usageTime = new GlobalTime();
    }

    //assign a user to the machine
    public void assignUser(User user) {
        assignedUser = user;
    }

    //unassign the user from the machine
    public void unassignUser() {
        assignedUser = null;
    }

    //get the name of the machine
    public String getName() {
        return name;
    }

    //set the name of the machine

    public void setName(String name) {
        this.name = name;
    }

    //reset usage time
    public void resetUsageTime(){
        usageTime = new GlobalTime();
    }

    //machine to string

    public String toString() {
        String availability = "";
        if(isAvailable) {
            availability = "Available";
        }
        else {
            availability = "Occupied";
        }
        return "Machine Name: " + name + "\nType: " + type + "\nAvailability: " + availability + "\nUsage Time: " + usageTime.getAgeFormatted() + "\n";
    }



}