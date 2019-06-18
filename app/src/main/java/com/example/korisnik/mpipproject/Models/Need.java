package com.example.korisnik.mpipproject.Models;

public class Need {
    private String name;

    public Need() {
    }

    private String priority;

    public Need(String name, String priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
