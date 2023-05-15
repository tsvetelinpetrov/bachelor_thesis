package com.tuvarna.mytu.models;

public class GraphNode {
    private int id;
    private double latitude;
    private double longitude;
    private boolean isValid;
    private boolean disabledPeople;
    private boolean forStudents;

    public GraphNode(int id, double latitude, double longitude, boolean isValid, boolean disabledPeople, boolean forStudents) {
        this.id = id;
        latitude = latitude;
        longitude = longitude;
        this.isValid = isValid;
        this.disabledPeople = disabledPeople;
        this.forStudents = forStudents;
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
        latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        longitude = longitude;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isDisabledPeople() {
        return disabledPeople;
    }

    public void setDisabledPeople(boolean disabledPeople) {
        this.disabledPeople = disabledPeople;
    }

    public boolean isForStudents() {
        return forStudents;
    }

    public void setForStudents(boolean forStudents) {
        this.forStudents = forStudents;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "id=" + id +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                ", isValid=" + isValid +
                ", disabledPeople=" + disabledPeople +
                ", forStudents=" + forStudents +
                '}';
    }
}
