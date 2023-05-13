package com.tuvarna.mytu.api;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;
import com.tuvarna.mytu.models.RoomDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/Building")
    Call<List<Building>> getBuildings();

    @GET("api/Building/details/{id}")
    Call<BuildingDetails> getBuildingDetails(@Path("id") int buildingId);

    @GET("api/Room/details/{id}")
    Call<RoomDetails> getRoomDetails(@Path("id") int roomId);
}
