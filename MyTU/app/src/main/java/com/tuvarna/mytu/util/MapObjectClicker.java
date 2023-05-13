package com.tuvarna.mytu.util;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Room;

public interface MapObjectClicker {
    void onBuildingClick(Building building);
    void onRoomClick(Room room);
}
