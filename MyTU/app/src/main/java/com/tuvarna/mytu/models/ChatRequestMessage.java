package com.tuvarna.mytu.models;

public class ChatRequestMessage {
    private String sender;
    private String message;

    public ChatRequestMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
