package com.example.advancedsoftwareengineering;

public class Transaction {
    private Service service;
    private State state;
    private User user;
    private double price;
    private boolean isRecurring;
    private GlobalTime timeAtCreation;

    private Machine machine;

    public enum State {
        PENDING,
        COMPLETED,
        REJECTED,
        RUNNING // for recurring transactions
    }

    public Transaction(Service service, State state, User user, GlobalTime timeAtCreation) {
        this.service = service;
        this.state = state;
        this.user = user;
        this.price = service.getPrice();
        this.isRecurring = service.isRecurring();
        this.timeAtCreation = timeAtCreation;
    }

    // Getters and setters for the attributes

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public GlobalTime getTimeAtCreation() {
        return timeAtCreation;
    }

    public void setTimeAtCreation(GlobalTime timeAtCreation) {
        this.timeAtCreation = timeAtCreation;
    }

    public boolean isRecurring(){
        return isRecurring;
    }

    public void setRecurring(boolean isRecurring){
        this.isRecurring = isRecurring;
    }

    //transaction to string
    public String toString() {
        String recurringIdentifier = "";
        if(this.isRecurring) {
            recurringIdentifier = "Price per minute: ";
        }
        else {
            recurringIdentifier = "Price: ";
        }
        return "Service Name: " + service.getServiceName() + "\nState: " + state + "\nUser: " + user.getName() + "\n" + recurringIdentifier + price + "\n";
    }

    //setCompleted

    public void setCompleted(){
        this.state = State.COMPLETED;
    }

    //setRejected

    public void setRejected(){
        this.state = State.REJECTED;
    }

    //setRunning

    public void setRunning(){
        this.state = State.RUNNING;
    }

    //setPending

    public void setPending(){
        this.state = State.PENDING;
    }




}