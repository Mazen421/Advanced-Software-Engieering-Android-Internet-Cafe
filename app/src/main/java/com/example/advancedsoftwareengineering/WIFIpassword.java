package com.example.advancedsoftwareengineering;

public class WIFIpassword {
    private static String password = "12345678";

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }
}
