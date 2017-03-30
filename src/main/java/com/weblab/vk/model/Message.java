package com.weblab.vk.model;

import com.weblab.model.User;
import lombok.ToString;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amowel on 18.03.17.
 */

public class Message {
    private String type;
    private int id;
    private int userId;
    private String body;
    private int groupId;
    private List<Message> forwardMessages;
    public Message()
    {
        forwardMessages = new ArrayList<>();
        attachment = new ArrayList<>();
    }
    public List<Message> getForwardMessages() {
        return forwardMessages;
    }

    public void setForwardMessages(List<Message> forwardMessages) {
        this.forwardMessages = forwardMessages;
    }

    public List<URL> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<URL> attachment) {
        this.attachment = attachment;
    }

    private List<URL> attachment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Message> getMessages() {
        return forwardMessages;
    }

    public void setMessages(List<Message> messages) {
        this.forwardMessages = messages;
    }
}
