package com.tuvarna.mytu.api;

import com.tuvarna.mytu.models.Building;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("api/Building")
    Call<List<Building>> getBuildings();
}
