package com.example.advancedsoftwareengineering;

public class Authenticator {

    //this class will take care of authenticating users and admins
    //it will take a user name and password and check if they are valid
    //by running them through the SERVER.users and SERVER.admins lists
    //if the user name and password are valid, it will return the user or admin as actor objects
    //it will only have one method called authenticate

    public static Actor authenticate(String username, String password){
        //first check if the user is an admin
        for (Sysadmin admin : SERVER.admins){
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        //if the user is not an admin, check if they are a user
        for (User user : SERVER.users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        //if the user is not an admin or a user, check cafe workers
        for (CafeWorker cafeWorker : SERVER.cafeWorkers){
            if (cafeWorker.getUsername().equals(username) && cafeWorker.getPassword().equals(password)){
                return cafeWorker;
            }
        }
        //if the user is not an admin, a user or a cafe worker, check IT workers

        for (ITworker itWorker : SERVER.itSupporters){
            if (itWorker.getUsername().equals(username) && itWorker.getPassword().equals(password)){
                return itWorker;
            }
        }
        return null;
    }




}
