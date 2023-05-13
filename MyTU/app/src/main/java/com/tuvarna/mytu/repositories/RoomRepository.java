package com.tuvarna.mytu.repositories;

import androidx.annotation.NonNull;

import com.tuvarna.mytu.api.ApiService;
import com.tuvarna.mytu.models.BuildingDetails;
import com.tuvarna.mytu.models.RoomDetails;
import com.tuvarna.mytu.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoomRepository implements IRoomRepository {
    private ApiService service;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public RoomRepository() {

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(ApiService.class);
    }

    @Override
    public void getRoomDetails(int roomId, IRoomsCallback callback) {
        try {
            Call<RoomDetails> call = service.getRoomDetails(roomId);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        RoomDetails roomDetails = (RoomDetails) response.body();
                        callback.onRoomDetailsReceived(roomDetails);
                    } else {
                        callback.onRoomDetailsReceiveFailure(new Throwable("HTTP Error code: "
                                + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callback.onRoomDetailsReceiveFailure(t);
                }
            });
        } catch (Exception e) {
            callback.onRoomDetailsReceiveFailure(e);
        }
    }
}