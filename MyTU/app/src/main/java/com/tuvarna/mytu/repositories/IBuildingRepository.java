package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IBuildingsCallback;

public interface IBuildingRepository {
    void getAllBuildings(IBuildingsCallback callback);
    void getBuildingDetails(int buildingId, IBuildingsCallback callback);
}
