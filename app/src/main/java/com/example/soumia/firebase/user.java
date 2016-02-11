package com.example.soumia.firebase;

/**
 * Created by soumia on 02/02/2016.
 */
public class user {
    private int birthYear;
    private String fullName;

    public user() {
    }

    public user(String fullName, int birthYear) {
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