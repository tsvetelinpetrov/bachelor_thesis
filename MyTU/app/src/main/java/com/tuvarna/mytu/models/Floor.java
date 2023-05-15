package com.tuvarna.mytu.models;

import java.util.List;

public class Floor {
    private List<Room> rooms;
    private int level;

    public Floor(List<Room> rooms, int level) {
        this.rooms = rooms;
        this.level = level;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "rooms=" + rooms +
                ", level=" + level +
                '}';
    }
}
