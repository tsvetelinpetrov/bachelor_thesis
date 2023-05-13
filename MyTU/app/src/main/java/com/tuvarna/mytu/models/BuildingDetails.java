package com.tuvarna.mytu.models;

public class BuildingDetails {
    private int id;
    private Building building;
    private String imageUrl;
    private String subTitle;
    private String description;

    public BuildingDetails(int id, Building building, String imageUrl, String subTitle, String description) {
        this.id = id;
        this.building = building;
        this.imageUrl = imageUrl;
        this.subTitle = subTitle;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BuildingDetails{" +
                "id=" + id +
                ", building=" + building +
                ", imageUrl='" + imageUrl + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
