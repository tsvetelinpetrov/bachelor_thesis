package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

public class Label {
    String text;
    int size;
    int bgColor;
    int fgColor;
    float rotation;
    double minZoom;
    double maxZoom;
    GeoPoint location;

    public Label(String text, int size, int bgColor, int fgColor, float rotation, double minZoom, double maxZoom, GeoPoint location) {
        this.text = text;
        this.size = size;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.rotation = rotation;
        this.minZoom = minZoom;
        this.maxZoom = maxZoom;
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getFgColor() {
        return fgColor;
    }

    public void setFgColor(int fgColor) {
        this.fgColor = fgColor;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public double getMinZoom() {
        return minZoom;
    }

    public void setMinZoom(double minZoom) {
        this.minZoom = minZoom;
    }

    public double getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(double maxZoom) {
        this.maxZoom = maxZoom;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Label{" +
                "text='" + text + '\'' +
                ", size=" + size +
                ", bgColor=" + bgColor +
                ", fgColor=" + fgColor +
                ", rotation=" + rotation +
                ", minZoom=" + minZoom +
                ", maxZoom=" + maxZoom +
                ", location=" + location +
                '}';
    }
}
