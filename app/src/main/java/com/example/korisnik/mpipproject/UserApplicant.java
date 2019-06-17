package com.example.korisnik.mpipproject;

public class UserApplicant {
    public String name;
    public String surname;
    public String needs;

    public String downloadUrl;
    public String latitude;
    public String longitude;

    public UserApplicant(){}

    public UserApplicant(String name, String surname, String needs, String downloadUrl, String latitude, String longitude)
    {
        this.name=name;
        this.surname=surname;
        this.needs=needs;
        this.downloadUrl=downloadUrl;
        this.latitude=latitude;
        this.longitude=longitude;
    }
}
