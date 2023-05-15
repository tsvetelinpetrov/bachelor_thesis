package com.tuvarna.mytu.views.custom;

import com.tuvarna.mytu.models.Room;

import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;

public class RoomPolygon extends Polygon {
    Room room;
    int floor;
    Marker labelMarker;

    public RoomPolygon(Room room, int floor, Marker labelMarker) {
        this.room = room;
        this.floor = floor;
        this.labelMarker = labelMarker;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Marker getLabelMarker() {
        return labelMarker;
    }

    public void setLabelMarker(Marker labelMarker) {
        this.labelMarker = labelMarker;
    }
}
