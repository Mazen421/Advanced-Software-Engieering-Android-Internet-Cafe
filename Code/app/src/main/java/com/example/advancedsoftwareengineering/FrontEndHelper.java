package com.example.advancedsoftwareengineering;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.advancedsoftwareengineering.ui.ItHandler.ItHandlerFragment;
import com.example.advancedsoftwareengineering.ui.UserBlacklist.UserBlacklistFragment;
import com.example.advancedsoftwareengineering.ui.UserHandler.UserHandlerFragment;
import com.example.advancedsoftwareengineering.ui.cafe.CafeFragment;
import com.example.advancedsoftwareengineering.ui.cafehandleradder.CafeServiceHandlerFragment;
import com.example.advancedsoftwareengineering.ui.credithandler.CreditHandlerFragment;
import com.example.advancedsoftwareengineering.ui.crediting.CreditingFragment;
import com.example.advancedsoftwareengineering.ui.machinehandler.MachineHandlerFragment;
import com.example.advancedsoftwareengineering.ui.stock.StockFragment;
import com.example.advancedsoftwareengineering.ui.userhome.UserHomeFragment;
import com.example.advancedsoftwareengineering.ui.reservation.ReservationFragment;
import com.example.advancedsoftwareengineering.ui.techservices.TechServicesFragment;
import com.example.advancedsoftwareengineering.ui.time.TimeFragment;
import com.example.advancedsoftwareengineering.ui.transactions.TransactionFragment;
import com.example.advancedsoftwareengineering.ui.wifi.WifiFragment;

public class FrontEndHelper {
    public static Fragment fragmentDecider(String name){
        Fragment fragment = null;
        switch(name){
            case "Home":
                fragment = new UserHomeFragment();
                break;
            case "Cafe":
                fragment = new CafeFragment();
                break;
            case "Tech Support":
                fragment = new TechServicesFragment();
                break;
            case "Wi-Fi":
                fragment = new WifiFragment();
                break;
            case "Reserve a Device":
                fragment = new ReservationFragment();
                break;
            case "Transaction History":
                fragment = new TransactionFragment();
                break;
            case "Time":
                fragment = new TimeFragment();
                break;
            case "Contact and Crediting":
                fragment = new CreditingFragment();
                break;
            case "Stock":
                fragment = new StockFragment();
                break;
            case "Inventory":
                fragment = new CafeServiceHandlerFragment();
                break;
            case "IT Service":
                fragment = new ItHandlerFragment();
                break;
            case "Machines":
                fragment = new MachineHandlerFragment();
                break;
            case "Manage Users":
                fragment = new UserHandlerFragment();
                break;
            case "Blacklist":
                fragment = new UserBlacklistFragment();
                break;
            case "Users Credit":
                fragment = new CreditHandlerFragment();
                break;

        }
        return fragment;
    }
    public static Bitmap getBitmapFromImage(Context context, int drawable) {
        // Get the drawable
        Drawable db = ContextCompat.getDrawable(context, drawable);

        // Create a bitmap with ARGB_8888 configuration
        Bitmap bit = Bitmap.createBitmap(
                db.getIntrinsicWidth(),
                db.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
        );

        // Create a canvas
        Canvas canvas = new Canvas(bit);

        // Set bounds for the bitmap
        db.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw the canvas
        db.draw(canvas);

        // Return the bitmap
        return bit;
    }
}




