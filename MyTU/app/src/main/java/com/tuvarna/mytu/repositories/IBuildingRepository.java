package com.tuvarna.mytu.repositories;

public interface IBuildingRepository {
    void getAllBuildings(BuildingsCallback callback);
    void getBuildingDetails(int buildingId, BuildingsCallback callback);
}
