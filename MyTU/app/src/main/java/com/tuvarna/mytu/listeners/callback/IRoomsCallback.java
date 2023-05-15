package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.RoomDetails;

public interface IRoomsCallback {
    /**
     * Called when room details are received successfully.
     *
     * @param roomDetails The RoomDetails object containing the details of the room.
     */
    void onRoomDetailsReceived(RoomDetails roomDetails);

    /**
     * Called when an error occurs while receiving room details.
     *
     * @param t The Throwable object representing the error.
     */
    void onRoomDetailsReceiveFailure(Throwable t);
}
