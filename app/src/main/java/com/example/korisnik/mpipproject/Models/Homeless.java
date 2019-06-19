package com.example.korisnik.mpipproject.Models;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.List;

public class Homeless {
    private String name;
    private String surname;
    private String imageUrl;
    private List<Need> needs;
    private LatLng location;
    private int age;
    private Date addedOn;
    private FirebaseUser addedBy;

    public Homeless(String name, String surname, String imageUrl, List<Need> needs, LatLng location, int age, FirebaseUser addedBy) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.needs = needs;
        this.location = location;
        this.age = age;
        this.addedBy = addedBy;
        this.addedOn = new Date();
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public FirebaseUser getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(FirebaseUser addedBy) {
        this.addedBy = addedBy;
    }

    public Homeless() {
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

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

}
