package com.example.advancedsoftwareengineering;

import java.util.ArrayList;
import java.util.List;

public class User extends Actor {
    private List<Transaction> transactionHistory;
    private Machine assignedMachine;
    private double credits;

    private boolean blacklisted = false;

    private boolean updateThreadRunning; //for timing the recurring transaction


    private Transaction recurringTransaction; //only one recurring transaction per user

    public User(String name, String username, String nationalId, String password) {
        super(name, username, nationalId, password);
        this.transactionHistory = new ArrayList<>();
        this.assignedMachine = null;
        this.credits = 0.0;
    }

    // Getters and setters for the attributes
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Machine getAssignedMachine() {
        return assignedMachine;
    }

    public void setAssignedMachine(Machine assignedMachine) {
        this.assignedMachine = assignedMachine;
        assignedMachine.setUnavailableAndAssignUser(this);
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    //decreses the credits of the user
    public void decreaseCredits(double amount) {
        credits -= amount;
    }

    //increases the credits of the user
    public void increaseCredits(double amount) {
        credits += amount;
    }



    // Additional method to add a transaction to the user's transaction history
    public void addTransactionToHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    // Additional method to remove a transaction from the user's transaction history
    public void removeTransactionFromHistory(Transaction transaction) {
        transactionHistory.remove(transaction);
    }

    // Additional method to get a transaction from the user's transaction history by index
    public Transaction getTransactionFromHistoryByIndex(int index) {
        return transactionHistory.get(index);
    }

    // Additional method to get a transaction from the user's transaction history by service name
    public Transaction getTransactionFromHistoryByServiceName(String serviceName) {
        for (Transaction transaction : transactionHistory) {
            if (transaction.getService().getServiceName().equals(serviceName)) {
                return transaction;
            }
        }
        return null;
    }

    // Additional method to request a service
    public void requestService(Service service) {

        if(blacklisted){
            System.out.println("You are blacklisted.");
            return;
        }

        if(service.getPrice() > credits){
            System.out.println("You don't have enough credits to request this service.");
            return;
        }
        if(service instanceof CafeItemService && !((CafeItemService) service).isAvailable()){
            System.out.println("This item is not available.");
            return;
        }




        if(service.isRecurring() && recurringTransaction != null){
            System.out.println("You already have a recurring transaction.");
            return;
        }
        if(service.isRecurring() && service instanceof PCService){
            PCService pcService = (PCService) service;
            if(!pcService.getMachine().isAvailable()){
                System.out.println("This machine is not available.");
                return;
            }
        }
        if(service.isRecurring() && service instanceof PlaystationService){
            PlaystationService psService = (PlaystationService) service;
            if(!psService.getMachine().isAvailable()){
                System.out.println("This machine is not available.");
                return;
            }
        }


        // Create a new transaction
        GlobalTime time = new GlobalTime();
        if(!service.isRecurring()){
            Transaction transaction = new Transaction(service, Transaction.State.PENDING, this, time);
            addTransactionToHistory(transaction);
            decreaseCredits(service.getPrice());
            if (service instanceof CafeItemService) {
                ((CafeItemService) service).decrementCount();
            }
            if(service instanceof ITSupportService && ((ITSupportService) service).getSupportType().equals("Wifi")){
                System.out.println(WIFIpassword.getPassword()); //change to a push notification
                transaction.setCompleted();
            }
            SERVER.pendingTransactions.add(transaction);
        }
        else {
            if (service instanceof PCService) {
                PCService pcService = (PCService) service;
                pcService.getMachine().setUnavailableAndAssignUser(this);
                Transaction transaction = new Transaction(pcService, Transaction.State.RUNNING, this, pcService.getMachine().getUsageTimeObject());
                addTransactionToHistory(transaction);
                recurringTransaction = transaction;
                startUpdateThread();


            } else if (service instanceof PlaystationService) {

                PlaystationService psService = (PlaystationService) service;
                psService.getMachine().setUnavailableAndAssignUser(this);
                Transaction transaction = new Transaction(psService, Transaction.State.RUNNING, this, psService.getMachine().getUsageTimeObject());
                addTransactionToHistory(transaction);
                recurringTransaction = transaction;
                startUpdateThread();


            }

        }

    }

    public void startUpdateThread() {
        if (!updateThreadRunning) {
            Thread updateThread = new Thread(() -> {
                while (updateThreadRunning) {
                    updateCredsRecurringTransaction();
                    try {
                        // Sleep for a minute (adjust as needed)
                        Thread.sleep(60010);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            updateThread.start();
            updateThreadRunning = true;
        }
    }

    public void stopUpdateThread() {
        updateThreadRunning = false;
    }

    // update the recurring transaction every minute using global time
    public void updateCredsRecurringTransaction(){
        if(recurringTransaction != null){
            if(recurringTransaction.getService() instanceof PCService){
                  PCService pcService = (PCService) recurringTransaction.getService();
                  if(pcService.getMachine().getUsageTimeObject().getAgeInMinutes() > 0){
                      decreaseCredits(pcService.getMachine().getUsageTimeObject().getAgeInMinutes() * pcService.getPrice());
                      pcService.getMachine().resetUsageTime();

                      if (credits <= 0) {
                          System.out.println("You don't have enough credits to continue this service.");
                          recurringTransaction.setState(Transaction.State.COMPLETED);
                          recurringTransaction = null;
                          pcService.getMachine().setAvailableAndUnassignUser();
                          stopUpdateThread();
                      }
                  }
            }
            else if(recurringTransaction.getService() instanceof PlaystationService){
                PlaystationService psService = (PlaystationService) recurringTransaction.getService();
                if(psService.getMachine().getUsageTimeObject().getAgeInMinutes() > 0){
                    decreaseCredits(psService.getMachine().getUsageTimeObject().getAgeInMinutes() * psService.getPrice());
                    psService.getMachine().resetUsageTime();
                }
                if (credits <= 0) {
                    System.out.println("You don't have enough credits to continue this service.");
                    recurringTransaction.setState(Transaction.State.REJECTED);
                    recurringTransaction = null;
                    psService.getMachine().setAvailableAndUnassignUser();
                    stopUpdateThread();
                }

            }
        }

    }

    // Additional method to cancel a pending service request
    public void cancelServiceRequest(Transaction transaction) {

        // Remove the transaction from the user's transaction history if it is pending
        if (transaction.getState() == Transaction.State.PENDING) {
            transaction.setRejected();
            SERVER.pendingTransactions.remove(transaction);
            this.increaseCredits(transaction.getService().getPrice());
        }
        else{
            System.out.println("Cannot cancel a service request that is not pending.");
        }

    }

    //stop the recurring transaction
    public void stopRecurringTransaction(){
        if(recurringTransaction != null){
            if(recurringTransaction.getService() instanceof PCService){
                PCService pcService = (PCService) recurringTransaction.getService();
                pcService.getMachine().setAvailableAndUnassignUser();
                recurringTransaction.setState(Transaction.State.COMPLETED);
                recurringTransaction = null;
                stopUpdateThread();
            }
            else if(recurringTransaction.getService() instanceof PlaystationService){
                PlaystationService psService = (PlaystationService) recurringTransaction.getService();
                psService.getMachine().setAvailableAndUnassignUser();
                recurringTransaction.setState(Transaction.State.COMPLETED);
                recurringTransaction = null;
                stopUpdateThread();
            }
        }
    }

    // Additional method to print the user's transaction history
    public void printTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void blacklistUser(){
        blacklisted = true;
    }

    public void unblacklistUser(){
        blacklisted = false;
    }

    public boolean isBlacklisted(){
        return blacklisted;
    }

    //toggle blacklisting
    public void toggleBlacklisting(){
        if(blacklisted){
            unblacklistUser();
        }
        else{
            blacklistUser();
        }
    }



    //user to string
    public String toString() {
        return "Name: " + getName() + "\nUsername: " + getUsername() + "\nNational ID: " + getNationalId() + "\nCredits: " + getCredits() + "\n";
    }



}
