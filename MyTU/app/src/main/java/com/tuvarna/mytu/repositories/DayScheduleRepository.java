package com.tuvarna.mytu.repositories;

import androidx.annotation.NonNull;

import com.tuvarna.mytu.api.ApiService;
import com.tuvarna.mytu.listeners.callback.IDayScheduleCallback;
import com.tuvarna.mytu.models.ScheduleDay;
import com.tuvarna.mytu.util.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DayScheduleRepository implements IDayScheduleRepository{
    private ApiService service;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public DayScheduleRepository() {
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
    public void getAllClasses(IDayScheduleCallback callback, String course, String specialty, String group) {
        try {
            Call<List<ScheduleDay>> call = service.getDaySchedules(course, specialty, group);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        List<ScheduleDay> daySchedules = (List<ScheduleDay>) response.body();
                        callback.onDayScheduleReceived(daySchedules);
                    } else {
                        callback.onDayScheduleReceiveFailure(new Throwable("HTTP Error code: " + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callback.onDayScheduleReceiveFailure(t);
                }
            });
        } catch (Exception e) {
            callback.onDayScheduleReceiveFailure(e);
        }
    }
}
