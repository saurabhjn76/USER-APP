package com.saurabhjn76.userapp;

/**
 * Created by saurabh on 27/5/16.
 */
public class Salon {
    private int birthYear;
    private String fullName;
    public Salon() {}
    public Salon(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }
    public long getBirthYear() {
        return birthYear;
    }
    public String getFullName() {
        return fullName;
    }
}
