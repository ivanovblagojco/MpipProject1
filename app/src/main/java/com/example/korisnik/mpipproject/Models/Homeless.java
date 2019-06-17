package com.example.korisnik.mpipproject.Models;

import android.location.Location;

import com.example.korisnik.mpipproject.Models.Need;
import com.example.korisnik.mpipproject.UserInfo;

import java.util.Date;
import java.util.List;

public class Homeless {
    private String name;
    private String surname;
    private String imageUrl;
    private List<Need> needs;
    private Location location;
    private int age;
    private Date addedOn;
    private UserInfo reportedBy;

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public UserInfo getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(UserInfo reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Homeless() {
    }

    public Homeless(String name, String surname, String imageUrl, List<Need> needs, Location location, int age) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.needs = needs;
        this.location = location;
        this.age = age;
        this.addedOn = new Date();
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Need> getNeeds() {
        return needs;
    }

    public Homeless(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public void setNeeds(List<Need> needs) {
        this.needs = needs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
