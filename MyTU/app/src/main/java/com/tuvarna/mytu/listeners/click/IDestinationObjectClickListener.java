package com.tuvarna.mytu.listeners.click;

import com.tuvarna.mytu.models.Room;

public interface IDestinationObjectClickListener {
    /**
     * Called when a destination object (room) is clicked.
     *
     * @param room The Room object representing the clicked room.
     */
    void onDirectionObjectClicked(Room room);
}