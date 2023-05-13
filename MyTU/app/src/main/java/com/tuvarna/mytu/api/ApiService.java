package com.tuvarna.mytu.api;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/Building")
    Call<List<Building>> getBuildings();

    @GET("api/Building/details/{id}")
    Call<BuildingDetails> getBuildingDetails(@Path("id") int buildingId);
}
