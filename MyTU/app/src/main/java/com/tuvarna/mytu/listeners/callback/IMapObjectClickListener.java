package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Room;

public interface IMapObjectClickListener {
    void onBuildingClick(Building building);
    void onRoomClick(Room room);
}
