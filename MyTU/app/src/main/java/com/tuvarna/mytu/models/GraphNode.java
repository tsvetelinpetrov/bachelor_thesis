package com.tuvarna.mytu.models;

public class GraphNode {
    private int id;
    private double latitude;
    private double longitude;
    private boolean isValid;
    private boolean disabledPeople;
    private boolean forStudents;
    private int floorLevel;

    public GraphNode(int id, double latitude, double longitude, boolean isValid, boolean disabledPeople, boolean forStudents, int floorLevel) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isValid = isValid;
        this.disabledPeople = disabledPeople;
        this.forStudents = forStudents;
        this.floorLevel = floorLevel;
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

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isValid=" + isValid +
                ", disabledPeople=" + disabledPeople +
                ", forStudents=" + forStudents +
                ", floorLevel=" + floorLevel +
                '}';
    }
}
