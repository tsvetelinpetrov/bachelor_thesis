package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Building {
    Label label;
    List<GeoPoint> points;
    int fillColor;
    int strokeColor;
    float strokeWidth;
    List<Floor> floors;

    public Building(Label label, List<GeoPoint> points, int fillColor, int strokeColor, float strokeWidth, List<Floor> floors) {
        this.label = label;
        this.points = points;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
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

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
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
                ", fillColor=" + fillColor +
                ", strokeColor=" + strokeColor +
                ", strokeWidth=" + strokeWidth +
                ", floors=" + floors +
                '}';
    }
}
