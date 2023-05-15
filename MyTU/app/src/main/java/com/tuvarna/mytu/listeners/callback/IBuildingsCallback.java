package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;

import java.util.List;

public interface IBuildingsCallback {
    /**
     * Called when the list of buildings is successfully received.
     *
     * @param buildings The list of buildings.
     */
    void onBuildingsReceived(List<Building> buildings);

    /**
     * Called when there is a failure in receiving the list of buildings.
     *
     * @param t The throwable object representing the failure.
     */
    void onBuildingsReceiveFailure(Throwable t);

    /**
     * Called when the building details are successfully received.
     *
     * @param buildingDetails The building details.
     */
    void onBuildingDetailsReceived(BuildingDetails buildingDetails);

    /**
     * Called when there is a failure in receiving the building details.
     *
     * @param t The throwable object representing the failure.
     */
    void onBuildingDetailsReceiveFailure(Throwable t);
}