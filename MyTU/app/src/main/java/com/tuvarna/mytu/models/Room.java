package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Room {
    Label label;
    int fillColor;
    int strokeColor;
    float strokeWidth;
    List<GeoPoint> points;

    public Room(Label label, int fillColor, int strokeColor, float strokeWidth, List<GeoPoint> points) {
        this.label = label;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.points = points;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
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
                ", fillColor=" + fillColor +
                ", strokeColor=" + strokeColor +
                ", strokeWidth=" + strokeWidth +
                ", points=" + points +
                '}';
    }
}
