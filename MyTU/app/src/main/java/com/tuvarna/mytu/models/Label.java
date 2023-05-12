package com.tuvarna.mytu.models;

import androidx.annotation.NonNull;

import org.osmdroid.util.GeoPoint;

public class Label {
    String text;
    String mapText;
    double mLatitude;
    double mLongitude;
    int icon;
    String iconColor;
    GeoPoint location;

    public Label(String text, String mapText, double mLatitude, double mLongitude, int icon, String iconColor) {
        this.text = text;
        this.mapText = mapText;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.icon = icon;
        this.iconColor = iconColor;
        this.location = new GeoPoint(mLatitude, mLongitude);
    }

    public Label(String text, String mapText, GeoPoint location) {
        this.text = text;
        this.mapText = mapText;
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

        public String getMapText() {
        return mapText;
    }

    public void setMapText(String mapText) {
        this.mapText = mapText;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
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
                ", mapText='" + mapText + '\'' +
                ", location=" + location +
                '}';
    }
}
