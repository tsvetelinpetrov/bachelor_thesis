package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.models.Building;

import java.util.List;

public interface BuildingsCallback {
    void onBuildingsReceived(List<Building> buildings);
    void onBuildingsReceiveFailure(Throwable t);
}
