package com.weblab.api;

import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by amowel on 06.04.17.
 */
@Component
public class MessagesApi extends Messages {


    public MessagesApi(VkApiClient vkExtendedApi) {
        super(vkExtendedApi);
    }

    @Override
    public MessagesSendQueryExtended send(Actor actor) {
        return new MessagesSendQueryExtended(getClient(),actor);
    }
}
