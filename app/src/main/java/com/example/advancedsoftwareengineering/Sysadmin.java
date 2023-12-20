package com.example.advancedsoftwareengineering;

public class Sysadmin extends Actor{


    public Sysadmin(String name, String username, String nationalId, String password) {
        super(name, username, nationalId, password);
    }





    public void createCafeWorkerAccount(String name, String username, String nationalId, String password){
        CafeWorker cafeWorker = new CafeWorker(name, username, nationalId, password);
        SERVER.cafeWorkers.add(cafeWorker);
    }

    //create IT worker account

    public void createITWorkerAccount(String name, String username, String nationalId, String password){
        ITworker itWorker = new ITworker(name, username, nationalId, password);
        SERVER.itSupporters.add(itWorker);
    }

    public void removeCafeWorkerAccount(CafeWorker cafeWorker){
        SERVER.cafeWorkers.remove(cafeWorker);
    }

    public void createUserAccount( String name, String username, String nationalId, String password){
        User user = new User(name, username, nationalId, password);
        SERVER.users.add(user);
    }

    public void removeUserAccount(User user){
        SERVER.users.remove(user);

    }


    public void createAdminAccount(String name, String username, String nationalId, String password){
        Sysadmin admin = new Sysadmin(name, username, nationalId, password);
        SERVER.admins.add(admin);

    }

    public void removeAdminAccount(Sysadmin admin){
        SERVER.admins.remove(admin);
    }


        //credit methods for user

    public void addCreditToUser(User user, int credit){
        user.increaseCredits(credit);

    }

    public void removeCreditFromUser(User user, int credit){
        user.decreaseCredits(credit);
    }

    public void stopUserRequrringService(User user){
        user.stopRecurringTransaction();
    }

    public void BlacklistUser(User user){
        user.blacklistUser();

    }

    public void UnBlacklistUser(User user){
        user.unblacklistUser();
    }

    //toggle blacklist

    public void toggleBlacklist(User user){
        user.toggleBlacklisting();
    }




}
