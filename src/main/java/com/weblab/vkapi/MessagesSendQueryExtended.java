package com.weblab.vkapi;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;


import java.util.List;

/**
 * Created by amowel on 06.04.17.
 */
public class MessagesSendQueryExtended extends MessagesSendQuery {

    public MessagesSendQueryExtended(VkApiClient client, Actor actor) {
        super(client, actor);
    }

    @Override
    public MessagesSendQueryExtended userId(Integer value) {
        return (MessagesSendQueryExtended)super.userId(value);
    }

    @Override
    public MessagesSendQueryExtended randomId(Integer value) {
        return (MessagesSendQueryExtended)super.randomId(value);
    }

    @Override
    public MessagesSendQueryExtended peerId(Integer value) {
        return (MessagesSendQueryExtended)super.peerId(value);
    }

    @Override
    public MessagesSendQueryExtended domain(String value) {
        return (MessagesSendQueryExtended)super.domain(value);
    }

    @Override
    public MessagesSendQueryExtended chatId(Integer value) {
        return (MessagesSendQueryExtended)super.chatId(value);
    }

    @Override
    public MessagesSendQueryExtended userIds(Integer... value) {
        return (MessagesSendQueryExtended)super.userIds(value);
    }

    @Override
    public MessagesSendQueryExtended userIds(List<Integer> value) {
        return (MessagesSendQueryExtended)super.userIds(value);
    }

    @Override
    public MessagesSendQueryExtended message(String value) {
        return (MessagesSendQueryExtended)super.message(value);
    }

    @Override
    public MessagesSendQueryExtended lat(Float value) {
        return (MessagesSendQueryExtended)super.lat(value);
    }

    @Override
    public MessagesSendQueryExtended lng(Float value) {
        return (MessagesSendQueryExtended)super.lng(value);
    }

    @Override
    public MessagesSendQueryExtended attachment(String... value) {
        return (MessagesSendQueryExtended)super.attachment(value);
    }

    @Override
    public MessagesSendQueryExtended attachment(List<String> value) {
        return (MessagesSendQueryExtended)super.attachment(value);
    }

    @Override
    public MessagesSendQueryExtended forwardMessages(String... value) {
        return (MessagesSendQueryExtended)super.forwardMessages(value);
    }

    @Override
    public MessagesSendQueryExtended forwardMessages(List<String> value) {
        return (MessagesSendQueryExtended)super.forwardMessages(value);
    }

    @Override
    public MessagesSendQueryExtended stickerId(Integer value) {
        return (MessagesSendQueryExtended)super.stickerId(value);
    }

    @Override
    public MessagesSendQueryExtended notification(Boolean value) {
        return (MessagesSendQueryExtended)super.notification(value);
    }

    @Override
    protected MessagesSendQueryExtended getThis() {
        return (MessagesSendQueryExtended)super.getThis();
    }

    public MessagesSendQueryExtended title(String value) {
        return (MessagesSendQueryExtended)unsafeParam("title", value);
    }

}
