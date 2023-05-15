package com.tuvarna.mytu.api;

import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.models.BuildingDetails;
import com.tuvarna.mytu.models.ChatMessage;
import com.tuvarna.mytu.models.ChatRequestMessage;
import com.tuvarna.mytu.models.NavigationRoute;
import com.tuvarna.mytu.models.ScheduleDay;
import com.tuvarna.mytu.models.RoomDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/Building")
    Call<List<Building>> getBuildings();

    @GET("api/Building/details/{id}")
    Call<BuildingDetails> getBuildingDetails(@Path("id") int buildingId);

    @GET("api/Navigation/getRoute/{startNodeId}/{endNodeId}")
    Call<NavigationRoute> getRoomDetails(@Path("startNodeId") int startNodeId, @Path("endNodeId") int endNodeId);

    @GET("api/Room/details/{id}")
    Call<RoomDetails> getRoomDetails(@Path("id") int roomId);

    @GET("api/DaySchedules/{course}/{specialty}/{group}")
    Call<List<ScheduleDay>> getDaySchedules(@Path("course") String course, @Path("specialty") String specialty, @Path("group") String group);

    @POST("webhooks/rest/webhook")
    Call<List<ChatMessage>> sendChatBotMessage(@Body ChatRequestMessage message);
}
