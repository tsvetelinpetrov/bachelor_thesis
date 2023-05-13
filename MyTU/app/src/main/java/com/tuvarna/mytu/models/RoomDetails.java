package com.tuvarna.mytu.models;

public class RoomDetails {
    private int id;
    private Room room;
    private String imageUrl;
    private String subTitle;
    private String description;

    public RoomDetails(int id, Room room, String imageUrl, String subTitle, String description) {
        this.id = id;
        this.room = room;
        this.imageUrl = imageUrl;
        this.subTitle = subTitle;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoomDetails{" +
                "id=" + id +
                ", room=" + room +
                ", imageUrl='" + imageUrl + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
