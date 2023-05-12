package com.tuvarna.mytu.util;

import com.tuvarna.mytu.models.Building;

import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;

public class BuildingPolygon extends Polygon {
    Building building;
    Marker labelMarker;

    public BuildingPolygon() {
        this.building = null;
        this.labelMarker = null;
    }

    public BuildingPolygon(Building building) {
        this.building = building;
        this.labelMarker = null;
    }

    public BuildingPolygon(Building building, Marker labelMarker) {
        this.building = building;
        this.labelMarker = labelMarker;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Marker getLabelMarker() {
        return labelMarker;
    }

    public void setLabelMarker(Marker labelMarker) {
        this.labelMarker = labelMarker;
    }
}
