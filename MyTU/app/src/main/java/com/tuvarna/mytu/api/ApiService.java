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
    /**
     * Retrieves a list of buildings.
     *
     * @return A Call object representing the asynchronous request for buildings.
     */
    @GET("api/Building")
    Call<List<Building>> getBuildings();

    /**
     * Retrieves the details of a specific building.
     *
     * @param buildingId The ID of the building.
     * @return A Call object representing the asynchronous request for building details.
     */
    @GET("api/Building/details/{id}")
    Call<BuildingDetails> getBuildingDetails(@Path("id") int buildingId);

    /**
     * Retrieves the navigation route between two nodes.
     *
     * @param startNodeId The ID of the start node.
     * @param endNodeId   The ID of the end node.
     * @return A Call object representing the asynchronous request for the navigation route.
     */
    @GET("api/Navigation/getRoute/{startNodeId}/{endNodeId}")
    Call<NavigationRoute> getNavigationRoute(@Path("startNodeId") int startNodeId, @Path("endNodeId") int endNodeId);

    /**
     * Retrieves the details of a specific room.
     *
     * @param roomId The ID of the room.
     * @return A Call object representing the asynchronous request for room details.
     */
    @GET("api/Room/details/{id}")
    Call<RoomDetails> getRoomDetails(@Path("id") int roomId);

    /**
     * Retrieves the schedules for a specific day.
     *
     * @param course    The course name.
     * @param specialty The specialty name.
     * @param group     The group name.
     * @return A Call object representing the asynchronous request for day schedules.
     */
    @GET("api/DaySchedules/{course}/{specialty}/{group}")
    Call<List<ScheduleDay>> getDaySchedules(@Path("course") String course, @Path("specialty") String specialty, @Path("group") String group);

    /**
     * Sends a chat bot message and retrieves the response.
     *
     * @param message The chat request message.
     * @return A Call object representing the asynchronous request for chat bot response.
     */
    @POST("webhooks/rest/webhook")
    Call<List<ChatMessage>> sendChatBotMessage(@Body ChatRequestMessage message);
}