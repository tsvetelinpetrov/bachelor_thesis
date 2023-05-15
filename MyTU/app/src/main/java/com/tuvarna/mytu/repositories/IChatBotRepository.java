package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IChatBotCallback;

public interface IChatBotRepository {
    /**
     * Retrieves a response from the chatbot.
     *
     * @param callback The callback to be invoked with the chatbot response.
     */
    void getChatBotResponse(IChatBotCallback callback);
}