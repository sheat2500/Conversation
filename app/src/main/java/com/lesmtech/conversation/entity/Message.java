package com.lesmtech.conversation.entity;

/**
 * @author Rindt
 * @version 0.1
 * @since 6/18/15
 */
public class Message {

    private String date;

    private String content;

    /**
     * true is sender(User)
     * false is server
     */
    private boolean sender;

    public Message() {

    }

    public Message(String date, String content, boolean sender) {
        this.date = date;
        this.content = content;
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSender() {
        return sender;
    }

    public void setSender(boolean sender) {
        this.sender = sender;
    }
}
