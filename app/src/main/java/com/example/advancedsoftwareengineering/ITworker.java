package com.example.advancedsoftwareengineering;


import android.graphics.Bitmap;


public class ITworker extends Actor{


    public ITworker(String name, String username, String nationalId, String password) {
        super(name, username, nationalId, password);
    }

    public void addITService(String name, int price, Bitmap image, String type){
        ITSupportService it = new ITSupportService(name, price, image, type);
        if(!name.equals("Wif")){
            SERVER.ITServices.add(it);
        }
    }



    public void removeITService(ITSupportService service){
        SERVER.ITServices.remove(service);
    }

    //increment the quantity of the item using stock up



    public void approvePendingTransaction(Transaction transaction){
        if(transaction.getService() instanceof ITSupportService){
            transaction.setCompleted();
        }
        SERVER.pendingTransactions.remove(transaction);
    }

    //reject pending transactions and reimburse the user

    public void rejectPendingTransaction(Transaction transaction){
        if(transaction.getService() instanceof ITSupportService){
            transaction.setRejected();
            SERVER.pendingTransactions.remove(transaction);
            transaction.getUser().increaseCredits(transaction.getService().getPrice());
        }

    }
    public void addMachineService(String machineName, String serviceType, int price){
        if(serviceType.equals("PC")){
            PCService pcService = new PCService(machineName, price, null);
            SERVER.PCservices.add(pcService);
        }
        else if (serviceType.equals("Playstation")){
            PlaystationService psService = new PlaystationService(machineName, price, null);
            SERVER.PlaystationServices.add(psService);
        }
    }



    public void removeMachineService(String machineName, String serviceType){
        if(serviceType.equals("PC")){
            for(PCService pcService : SERVER.PCservices){
                if(pcService.getMachine().getName().equals(machineName)){
                    SERVER.PCservices.remove(pcService);
                }
            }
        }
        else if (serviceType.equals("Playstation")){
            for(PlaystationService psService : SERVER.PlaystationServices){
                if(psService.getMachine().getName().equals(machineName)){
                    SERVER.PlaystationServices.remove(psService);
                }
            }
        }

    }

    public void setWiFiPassword(String newpass){
        WIFIpassword.setPassword(newpass);
    }


}
