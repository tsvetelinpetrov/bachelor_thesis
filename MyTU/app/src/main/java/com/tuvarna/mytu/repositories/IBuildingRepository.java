package com.tuvarna.mytu.repositories;

public interface IBuildingRepository {
    void getAllBuildings(IBuildingsCallback callback);
    void getBuildingDetails(int buildingId, IBuildingsCallback callback);
}
