package com.tuvarna.mytu.listeners.click;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.Room;

public interface IMapObjectClickListener {
    /**
     * Called when a building is clicked on the map.
     *
     * @param building The Building object representing the clicked building.
     */
    void onBuildingClick(Building building);

    /**
     * Called when a room is clicked on the map.
     *
     * @param room The Room object representing the clicked room.
     */
    void onRoomClick(Room room);
}