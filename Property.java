package com.im.tut.models;

public class Property {

    private int id;
    private double latitude;
    private double longitude;
    int bedrooms;
    int bathrooms;
    int cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Property(int id, double latitude, double longitude, int bedrooms, int bathrooms, int cost) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.cost = cost;
    }

}
