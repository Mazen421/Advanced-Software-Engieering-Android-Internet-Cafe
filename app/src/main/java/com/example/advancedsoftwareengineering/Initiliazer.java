package com.example.advancedsoftwareengineering;

import android.content.Context;

public class Initiliazer {

    public static void initilizeUsers(){
        Sysadmin root = new Sysadmin("root", "root", "root", "root");
        root.createAdminAccount("root", "root", "root", "root");
        root.createUserAccount("user1", "user1", "301020545488", "user1");
        root.createUserAccount("user2", "user2", "102030405060", "user2");
        root.createAdminAccount("admin1","admin1","admin1","admin1");
        root.createAdminAccount("admin2","admin2","admin2","admin2");
        root.createITWorkerAccount("it1","it1","it1","it1");
        root.createITWorkerAccount("it2","it2","it2","it2");
        root.createCafeWorkerAccount("cafe1","cafe1","cafe1","cafe1");
        root.createCafeWorkerAccount("cafe2","cafe2","cafe2","cafe2");

    }

    public static void initilizeCafe(Context context){
        CafeWorker cafe1 = (CafeWorker) Authenticator.authenticate("cafe1", "cafe1");
        assert cafe1 != null;
        cafe1.addCafeService("Pretzel", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.pretzel), "Food");
        cafe1.addCafeService("Sandwich", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.sandwich), "Food");
        cafe1.addCafeService("Chocolate\nMilk", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.choccy), "Drink");
        cafe1.addCafeService("Apple\nJuice Box", 10, FrontEndHelper.getBitmapFromImage(context, R.drawable.apple_juice_box), "Drink");
        cafe1.addCafeService("Tea", 10, FrontEndHelper.getBitmapFromImage(context, R.drawable.tea), "Drink");
        cafe1.addCafeService("Water", 8, FrontEndHelper.getBitmapFromImage(context, R.drawable.water), "Drink");
        cafe1.addCafeService("Chips", 10, FrontEndHelper.getBitmapFromImage(context, R.drawable.chips), "Food");
        cafe1.addCafeService("Croissant", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.croissant), "Food");
        cafe1.addCafeService("Energy\nDrink", 20, FrontEndHelper.getBitmapFromImage(context, R.drawable.energy_drink), "Drink");
        cafe1.addCafeService("Chocolate\nMilkshake", 25, FrontEndHelper.getBitmapFromImage(context, R.drawable.milkshake_chocolate), "Drink");
        cafe1.addCafeService("Strawberry\nMilkshake", 25, FrontEndHelper.getBitmapFromImage(context, R.drawable.milkshake_strawberry), "Drink");
        cafe1.addCafeService("Black\nCoffee", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.cup_of_coffee_black), "Drink");
        cafe1.addCafeService("Latte", 15, FrontEndHelper.getBitmapFromImage(context, R.drawable.latte), "Drink");




    }
}
