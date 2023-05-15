package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.listeners.callback.IChatBotCallback;

public interface IChatBotRepository {
    void getChatBotResponse(IChatBotCallback callback);
}
