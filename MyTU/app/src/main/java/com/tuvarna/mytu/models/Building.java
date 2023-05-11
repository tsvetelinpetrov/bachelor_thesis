package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Building {
    Label label;
    List<GeoPoint> points;
    List<Floor> floors;

    public Building(Label label, List<GeoPoint> points, List<Floor> floors) {
        this.label = label;
        this.points = points;
        this.floors = floors;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<GeoPoint> getPoints() {
        return points;
    }

    public void setPoints(List<GeoPoint> points) {
        this.points = points;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "Building{" +
                "label=" + label +
                ", points=" + points +
                ", floors=" + floors +
                '}';
    }
}
