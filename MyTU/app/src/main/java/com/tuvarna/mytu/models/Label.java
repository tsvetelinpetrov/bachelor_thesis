package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

public class Label {
    private String text;
    private String subText;
    private String mapText;
    private double mLatitude;
    private double mLongitude;
    private int icon;
    private String iconColor;
    private GeoPoint location;

    public Label(String text, String subText, String mapText, double mLatitude, double mLongitude, int icon, String iconColor) {
        this.text = text;
        this.subText = subText;
        this.mapText = mapText;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.icon = icon;
        this.iconColor = iconColor;
        this.location = new GeoPoint(mLatitude, mLongitude);
    }

    public Label(String text, String subText, String mapText, GeoPoint location) {
        this.text = text;
        this.subText = subText;
        this.mapText = mapText;
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
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

    @Override
    public String toString() {
        return "Label{" +
                "text='" + text + '\'' +
                ", subText='" + subText + '\'' +
                ", mapText='" + mapText + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", icon=" + icon +
                ", iconColor='" + iconColor + '\'' +
                ", location=" + location +
                '}';
    }
}
