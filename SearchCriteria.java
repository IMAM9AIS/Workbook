package com.im.tut.models;

public class SearchCriteria {

    private int id;
    private double latitude;
    private double longitude;
    private int minBudget;
    private int maxBudget;
    private int minBedroom;
    private int maxBedroom;
    private  int minBathroom;
    private int maxBathroom;

    public SearchCriteria(int id, double latitude, double longitude, int minBudget, int maxBudget, int minBedroom, int maxBedroom, int minBathroom, int maxBathroom) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.minBedroom = minBedroom;
        this.maxBedroom = maxBedroom;
        this.minBathroom = minBathroom;
        this.maxBathroom = maxBathroom;
    }

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

    public int getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(int minBudget) {
        this.minBudget = minBudget;
    }

    public int  getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(int maxBudget) {
        this.maxBudget = maxBudget;
    }

    public int getMinBedroom() {
        return minBedroom;
    }

    public void setMinBedroom(int minBedroom) {
        this.minBedroom = minBedroom;
    }

    public int getMaxBedroom() {
        return maxBedroom;
    }

    public void setMaxBedroom(int maxBedroom) {
        this.maxBedroom = maxBedroom;
    }

    public int getMinBathroom() {
        return minBathroom;
    }

    public void setMinBathroom(int minBathroom) {
        this.minBathroom = minBathroom;
    }

    public int getMaxBathroom() {
        return maxBathroom;
    }

    public void setMaxBathroom(int maxBathroom) {
        this.maxBathroom = maxBathroom;
    }
}
