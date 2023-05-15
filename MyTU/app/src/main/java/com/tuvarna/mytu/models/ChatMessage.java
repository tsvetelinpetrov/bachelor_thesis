package com.tuvarna.mytu.models;

public class ChatMessage {
    private String recipient_id;
    private String sender;
    private String text;
    private String avatar;

    public ChatMessage() {
        this.recipient_id = null;
        this.sender = null;
        this.text = "";
        this.avatar = "";
    }

    public ChatMessage(String recipient_id, String sender, String text) {
        this.recipient_id = recipient_id;
        this.sender = sender;
        this.text = text;
        avatar = "";
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "recipient_id='" + recipient_id + '\'' +
                ", sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
