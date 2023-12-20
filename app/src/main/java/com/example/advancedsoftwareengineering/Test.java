package com.example.advancedsoftwareengineering;

import static java.lang.Thread.sleep;

public class Test {
    public static void main(String[] args) {

        Sysadmin root = new Sysadmin("root", "root", "root", "root");
        root.createUserAccount("user1", "user1", "user1", "user1");
        User user1 = (User)Authenticator.authenticate("user1", "user1");
        assert user1 != null;
        root.addCreditToUser(user1, 100);
        System.out.println("-------------------------------");
        root.BlacklistUser(user1);

        user1.requestService(SERVER.PCservices.get(0));
        root.createCafeWorkerAccount("cafe1", "cafe1", "cafe1", "cafe1");
        CafeWorker cafe1 = (CafeWorker)Authenticator.authenticate("cafe1", "cafe1");
        assert cafe1 != null;
        cafe1.addCafeService("coffee", 10, null, "drink");
        root.toggleBlacklist(user1);
        user1.requestService(SERVER.cafeServices.get(0));
        System.out.println("SHOULD BE PENIDNG-------------------------------");
        user1.printTransactionHistory();
        cafe1.rejectPendingTransaction(user1.getTransactionFromHistoryByIndex(0));
        System.out.println("SHOULD BE REJECTED-------------------------------");
        user1.printTransactionHistory();
        user1.requestService(SERVER.cafeServices.get(0));
        cafe1.approvePendingTransaction(SERVER.pendingTransactions.get(0));
        System.out.println("SHOULD BE COMPLETED-------------------------------");
        user1.printTransactionHistory();
        root.createITWorkerAccount("it1", "it1", "it1", "it1");
        ITworker it1 = (ITworker)Authenticator.authenticate("it1", "it1");
        assert it1 != null;
        it1.addMachineService("PC1", "PC", 10);
        it1.addITService("Monitor issue", 10, null, "TechSupport");
        user1.requestService(SERVER.ITServices.get(0));
        it1.approvePendingTransaction(SERVER.pendingTransactions.get(0));
        System.out.println("SHOULD BE COMPLETED-------------------------------");
        user1.printTransactionHistory();
        System.out.println("-------------------------------");
        System.out.println(user1);
        System.out.println("-------------------------------");
        //now for wifi, which should have no pending state
        it1.addITService("Wifi password", 10, null, "Wifi");
        user1.requestService(SERVER.ITServices.get(1));
        user1.printTransactionHistory();
        System.out.println("-----------------\n" + user1.getCredits() + "\n-----------------");
    }
}


