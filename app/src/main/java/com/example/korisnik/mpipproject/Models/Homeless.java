package com.example.korisnik.mpipproject.Models;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Homeless implements Comparable<Homeless>{
    private String name;
    private String surname;
    private String imageUrl;
    private String needs;
    private double latitude;
    private double longitude;
    private int age;
    private String addedOn;
    private float distance;

    public String getAddedOn() {
        return addedOn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }


    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public Homeless(String name, String surname, String imageUrl, String needs, int age, double latitude, double longitude) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.needs = needs;
        this.latitude = latitude;
        this.longitude = longitude;
        this.age = age;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.addedOn = dateFormat.format(date);
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

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int compareTo(@NonNull Homeless o) {

        if (this.distance > o.getDistance()) {
            return 1;
        }
        else if (this.distance <  o.getDistance()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Homeless{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", needs='" + needs + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", age=" + age +
                ", addedOn='" + addedOn + '\'' +
                ", distance=" + distance +
                '}';
    }
}
