package com.example.korisnik.mpipproject.Models;

public class UserInfo {
    private String UserID;
    public String name;
    public String address;

    public UserInfo(){}

    public UserInfo(String userID, String name, String address) {
        UserID = userID;
        this.name = name;
        this.address = address;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserInfo(String name, String address)
    {
        this.name=name;

        this.address=address;
    }

}
