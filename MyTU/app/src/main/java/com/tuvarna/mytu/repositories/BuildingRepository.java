package com.tuvarna.mytu.repositories;

import androidx.annotation.NonNull;

import com.tuvarna.mytu.api.ApiService;
import com.tuvarna.mytu.models.Building;
import com.tuvarna.mytu.util.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingRepository implements IBuildingRepository {
    private ApiService service;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public BuildingRepository() {

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
    public void getAllBuildings(BuildingsCallback callback) {
        try {
            Call<List<Building>> call = service.getBuildings();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        List<Building> buildings = (List<Building>) response.body();
                        callback.onBuildingsReceived(buildings);
                    } else {
                        callback.onBuildingsReceiveFailure(new Throwable("HTTP Error code: " + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callback.onBuildingsReceiveFailure(t);
                }
            });
        } catch (Exception e) {
            callback.onBuildingsReceiveFailure(e);
        }
    }
}
