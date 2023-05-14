package com.tuvarna.mytu.models;

public class GraphNodesEdge {
    private int id;
    private GraphNode nodeX;
    private GraphNode nodeY;
    private double weight;
    private boolean isValid;
    private boolean disabledPeople;
    private boolean forStudents;

    public GraphNodesEdge(int id, GraphNode nodeX, GraphNode nodeY, double weight, boolean isValid, boolean disabledPeople, boolean forStudents) {
        this.id = id;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.weight = weight;
        this.isValid = isValid;
        this.disabledPeople = disabledPeople;
        this.forStudents = forStudents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GraphNode getNodeX() {
        return nodeX;
    }

    public void setNodeX(GraphNode nodeX) {
        this.nodeX = nodeX;
    }

    public GraphNode getNodeY() {
        return nodeY;
    }

    public void setNodeY(GraphNode nodeY) {
        this.nodeY = nodeY;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isDisabledPeople() {
        return disabledPeople;
    }

    public void setDisabledPeople(boolean disabledPeople) {
        this.disabledPeople = disabledPeople;
    }

    public boolean isForStudents() {
        return forStudents;
    }

    public void setForStudents(boolean forStudents) {
        this.forStudents = forStudents;
    }

    @Override
    public String toString() {
        return "GraphNodesEdge{" +
                "id=" + id +
                ", nodeX=" + nodeX +
                ", nodeY=" + nodeY +
                ", weight=" + weight +
                ", isValid=" + isValid +
                ", disabledPeople=" + disabledPeople +
                ", forStudents=" + forStudents +
                '}';
    }
}
