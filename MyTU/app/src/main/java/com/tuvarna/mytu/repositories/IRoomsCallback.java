package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.models.RoomDetails;

public interface IRoomsCallback {
    void onRoomDetailsReceived(RoomDetails roomDetails);
    void onRoomDetailsReceiveFailure(Throwable t);
}
