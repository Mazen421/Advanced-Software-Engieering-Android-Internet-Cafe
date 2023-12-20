package com.example.advancedsoftwareengineering;

import android.graphics.Bitmap;

public class CafeWorker extends Actor{


    public CafeWorker(String name, String username, String nationalId, String password) {
        super(name, username, nationalId, password);
    }

    public void addCafeService(String name, int price, Bitmap image, String type){
        CafeItemService cafeItemService = new CafeItemService(name, price, image, type);
        SERVER.cafeServices.add(cafeItemService);
    }

    public void removeCafeService(CafeItemService service){
        SERVER.cafeServices.remove(service);
    }

    //increment the quantity of the item using stock up

    public void stockUpCafeService(CafeItemService service, int quantity){
        service.stockUp(quantity);
    }

    //mark is out of stock by setting the quantity to 0

    public void markOutOfStock(CafeItemService service){
        service.setCount(0);
    }

    //approve pending transactions if they are not out of stock and are cafe services

    public void approvePendingTransaction(Transaction transaction){
        if(transaction.getService() instanceof CafeItemService){
            transaction.setCompleted();
        }
        SERVER.pendingTransactions.remove(transaction);
    }

    //reject pending transactions and reimburse the user

    public void rejectPendingTransaction(Transaction transaction){
        if(transaction.getService() instanceof CafeItemService){
            transaction.setRejected();
            SERVER.pendingTransactions.remove(transaction);
            //reimburse the user
            transaction.getUser().increaseCredits(transaction.getService().getPrice());

        }


    }



}
