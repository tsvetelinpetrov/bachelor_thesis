package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Room {
    private int id;
    Label label;
    List<GeoPoint> points;

    public Room(int id, Label label, List<GeoPoint> points) {
        this.id = id;
        this.label = label;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", label=" + label +
                ", points=" + points +
                '}';
    }
}
