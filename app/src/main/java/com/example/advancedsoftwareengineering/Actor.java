package com.example.advancedsoftwareengineering;

public abstract class Actor {
    private String name;
    private String username;
    private String nationalId;
    private String password;

    public Actor(String name, String username, String nationalId, String password) {
        this.name = name;
        this.username = username;
        this.nationalId = nationalId;
        this.password = password;
    }

    // Getters and setters for the attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
