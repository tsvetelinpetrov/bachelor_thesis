package com.tuvarna.mytu.repositories;

import java.io.IOException;

public interface IBuildingRepository {
    void getAllBuildings(BuildingsCallback callback);
}
