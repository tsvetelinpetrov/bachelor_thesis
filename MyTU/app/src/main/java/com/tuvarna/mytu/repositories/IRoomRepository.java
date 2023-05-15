package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IRoomsCallback;

public interface IRoomRepository {
    /**
     * Retrieves the details of a room.
     *
     * @param roomId        The ID of the room.
     * @param callback      The callback to be invoked with the retrieved room details.
     */
    void getRoomDetails(int roomId, IRoomsCallback callback);
}