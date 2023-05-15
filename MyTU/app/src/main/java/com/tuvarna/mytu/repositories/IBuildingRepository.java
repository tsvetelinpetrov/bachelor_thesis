package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IBuildingsCallback;

public interface IBuildingRepository {
    /**
     * Retrieves all buildings.
     *
     * @param callback The callback to be invoked with the list of buildings.
     */
    void getAllBuildings(IBuildingsCallback callback);

    /**
     * Retrieves the details of a specific building.
     *
     * @param buildingId The ID of the building.
     * @param callback   The callback to be invoked with the building details.
     */
    void getBuildingDetails(int buildingId, IBuildingsCallback callback);
}