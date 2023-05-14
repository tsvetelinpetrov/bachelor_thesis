package com.tuvarna.mytu.models;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class Building {
    private int id;
    private Label label;
    private List<GeoPoint> points;
    private List<Floor> floors;
    private GraphNode graphNode;

    public Building(int id, Label label, List<GeoPoint> points, List<Floor> floors, GraphNode graphNode) {
        this.id = id;
        this.label = label;
        this.points = points;
        this.floors = floors;
        this.graphNode = graphNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<GeoPoint> getPoints() {
        return points;
    }

    public void setPoints(List<GeoPoint> points) {
        this.points = points;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public GraphNode getGraphNode() {
        return graphNode;
    }

    public void setGraphNode(GraphNode graphNode) {
        this.graphNode = graphNode;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", label=" + label +
                ", points=" + points +
                ", floors=" + floors +
                ", graphNode=" + graphNode +
                '}';
    }
}
