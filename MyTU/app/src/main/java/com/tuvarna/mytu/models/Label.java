package com.tuvarna.mytu.models;

import androidx.annotation.NonNull;

import org.osmdroid.util.GeoPoint;

public class Label {
    String text;
    GeoPoint location;

    public Label(String text, GeoPoint location) {
        this.text = text;
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    @NonNull
    @Override
    public String toString() {
        return "Label{" +
                "text='" + text + '\'' +
                ", location=" + location +
                '}';
    }
}
