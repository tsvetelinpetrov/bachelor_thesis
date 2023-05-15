package com.tuvarna.mytu.models;

import java.util.List;

public class NavigationRoute {
    private final List<GraphNode> nodes;
    private final double distance;
    private final boolean isValid;
    private final boolean disabledPeople;
    private final boolean forStudents;

    public NavigationRoute(List<GraphNode> nodes, double distance, boolean isValid, boolean disabledPeople, boolean forStudents) {
        this.nodes = nodes;
        this.distance = distance;
        this.isValid = isValid;
        this.disabledPeople = disabledPeople;
        this.forStudents = forStudents;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isDisabledPeople() {
        return disabledPeople;
    }

    public boolean isForStudents() {
        return forStudents;
    }

    @Override
    public String toString() {
        return "NavigationRoute{" +
                "nodes=" + nodes +
                ", distance=" + distance +
                ", isValid=" + isValid +
                ", disabledPeople=" + disabledPeople +
                ", forStudents=" + forStudents +
                '}';
    }
}
