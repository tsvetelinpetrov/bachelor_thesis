package com.tuvarna.mytu.repositories;

public interface IRoomRepository {
    void getRoomDetails(int roomId, IRoomsCallback callback);
}
