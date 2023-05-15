package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IRoomsCallback;

public interface IRoomRepository {
    void getRoomDetails(int roomId, IRoomsCallback callback);
}
