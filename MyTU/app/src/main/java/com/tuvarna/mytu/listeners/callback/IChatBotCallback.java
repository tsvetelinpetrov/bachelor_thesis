package com.tuvarna.mytu.listeners.callback;

import com.tuvarna.mytu.models.ChatMessage;

import java.util.ArrayList;

public interface IChatBotCallback {
    /**
     * Called when the chatbot response is successfully received.
     *
     * @param chatMessageResponse The list of chat messages representing the chatbot response.
     */
    void onChatBotResponseReceived(ArrayList<ChatMessage> chatMessageResponse);

    /**
     * Called when there is a failure in receiving the chatbot response.
     *
     * @param t The throwable object representing the failure.
     */
    void onChatBotResponseFailure(Throwable t);
}