package com.tuvarna.mytu.repositories;

import androidx.annotation.NonNull;

import com.tuvarna.mytu.api.ApiService;
import com.tuvarna.mytu.listeners.click.INavigationCallback;
import com.tuvarna.mytu.models.NavigationRoute;
import com.tuvarna.mytu.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NavigationRepository implements INavigationRepository {
    private ApiService service;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public NavigationRepository() {
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
    public void getNavigationRoute(INavigationCallback callback, int startNodeId, int endNodeId) {
        try {
            Call<NavigationRoute> call = service.getRoomDetails(startNodeId, endNodeId);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        NavigationRoute navigationRoute = (NavigationRoute) response.body();
                        callback.onGetRouteSuccess(navigationRoute);
                    } else {
                        callback.onGetRouteFailure(new Throwable("HTTP Error code: " + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callback.onGetRouteFailure(t);
                }
            });
        } catch (Exception e) {
            callback.onGetRouteFailure(e);
        }
    }
}
