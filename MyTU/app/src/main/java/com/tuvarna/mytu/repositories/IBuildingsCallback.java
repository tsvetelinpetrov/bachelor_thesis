package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;

import java.util.List;

public interface IBuildingsCallback {
    void onBuildingsReceived(List<Building> buildings);
    void onBuildingsReceiveFailure(Throwable t);

    void onBuildingDetailsReceived(BuildingDetails buildingDetails);
    void onBuildingDetailsReceiveFailure(Throwable t);
}
