package com.avans2018.klasd.cineapp.domain;

import java.util.ArrayList;

/**
 * Created by HeyRobin on 26-3-2018.
 * Last Edited by Robin on 26-03-18.
 */

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String emailAddress;
    private int phoneNumber;
    private int houseNumber;
    private String streetName;
    private String city;
    private ArrayList<Reservation> reservations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }

    public boolean hasReservation() {
        if (this.reservations != null)   {
            return true;
        } else  {
            return false;
        }
    }
}
