package com.example.advancedsoftwareengineering;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalTime {
    private final long creationTime;

    public GlobalTime() {
        this.creationTime = System.currentTimeMillis();
    }

    // Get the age in milliseconds
    public long getAgeInMillis() {
        return System.currentTimeMillis() - creationTime;
    }

    // Get the age in seconds
    public long getAgeInSeconds() {
        return getAgeInMillis() / 1000;
    }

    // Get the age in minutes
    public long getAgeInMinutes() {
        return getAgeInSeconds() / 60;
    }

    // Get the age in hours
    public long getAgeInHours() {
        return getAgeInMinutes() / 60;
    }

    // Print the age formatted
    public String getAgeFormatted() {
        long seconds = getAgeInSeconds() % 60;
        long minutes = getAgeInMinutes() % 60;
        long hours = getAgeInHours();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // Print the time at creation formatted
    public String getTimeAtCreationFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(creationTime));
    }

    // Compare with another GlobalTime object
    public int compareTo(GlobalTime otherTime) {
        return Long.compare(this.creationTime, otherTime.creationTime);
    }

    // Difference with another GlobalTime object in minutes
    public long differenceInMinutes(GlobalTime otherTime) {
        return Math.abs(this.getAgeInMinutes() - otherTime.getAgeInMinutes());
    }

    // Difference with another GlobalTime object in hours

    public long differenceInHours(GlobalTime otherTime) {
        return Math.abs(this.getAgeInHours() - otherTime.getAgeInHours());
    }

    // Difference with another GlobalTime object in seconds

    public long differenceInSeconds(GlobalTime otherTime) {
        return Math.abs(this.getAgeInSeconds() - otherTime.getAgeInSeconds());
    }
}