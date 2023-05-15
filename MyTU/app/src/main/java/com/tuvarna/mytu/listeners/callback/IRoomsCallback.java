package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.RoomDetails;

public interface IRoomsCallback {
    void onRoomDetailsReceived(RoomDetails roomDetails);
    void onRoomDetailsReceiveFailure(Throwable t);
}
