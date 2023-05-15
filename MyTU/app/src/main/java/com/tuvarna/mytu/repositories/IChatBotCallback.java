package com.tuvarna.mytu.repositories;

import com.tuvarna.mytu.models.ChatMessage;

import java.util.ArrayList;

public interface IChatBotCallback {
    void onChatBotResponseReceived(ArrayList<ChatMessage> chatMessageResponse);
    void onChatBotResponseFailure(Throwable t);
}
