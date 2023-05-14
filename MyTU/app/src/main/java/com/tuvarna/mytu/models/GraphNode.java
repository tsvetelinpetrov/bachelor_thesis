package com.tuvarna.mytu.models;

public class GraphNode {
    private int id;
    private double Latitude;
    private double Longitude;
    private boolean isValid;
    private boolean disabledPeople;
    private boolean forStudents;

    public GraphNode(int id, double latitude, double longitude, boolean isValid, boolean disabledPeople, boolean forStudents) {
        this.id = id;
        Latitude = latitude;
        Longitude = longitude;
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
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
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
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", isValid=" + isValid +
                ", disabledPeople=" + disabledPeople +
                ", forStudents=" + forStudents +
                '}';
    }
}
