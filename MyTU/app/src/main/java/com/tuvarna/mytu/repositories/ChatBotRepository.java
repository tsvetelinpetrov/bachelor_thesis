package com.tuvarna.mytu.repositories;

import androidx.annotation.NonNull;

import com.tuvarna.mytu.api.ApiService;
import com.tuvarna.mytu.models.ChatMessage;
import com.tuvarna.mytu.models.ChatRequestMessage;
import com.tuvarna.mytu.util.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotRepository {
    Integer funnyCounter = 0;
    private ApiService service;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public ChatBotRepository() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.CHATBOT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        service = retrofit.create(ApiService.class);
    }

    public void getChatBotResponse(ChatRequestMessage message, IChatBotCallback callback) {
        try {
            Call<List<ChatMessage>> call = service.sendChatBotMessage(message);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        ArrayList<ChatMessage> chatResponseMessage = (ArrayList<ChatMessage>) response.body();
                        callback.onChatBotResponseReceived(chatResponseMessage);
                    } else {
                        callback.onChatBotResponseFailure(new Throwable("HTTP Error code: " + response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    callback.onChatBotResponseFailure(t);
                }
            });
        } catch (Exception e) {
            callback.onChatBotResponseFailure(e);
        }
    }
}//В колко часа отваря стола?
