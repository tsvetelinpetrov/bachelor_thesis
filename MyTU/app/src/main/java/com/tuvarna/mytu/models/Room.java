package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Room {
    Label label;
    List<GeoPoint> points;

    public Room(Label label, List<GeoPoint> points) {
        this.label = label;
        this.points = points;
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

    @Override
    public String toString() {
        return "Room{" +
                "label=" + label +
                ", points=" + points +
                '}';
    }
}
