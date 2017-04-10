package com.weblab.api;

import com.google.gson.Gson;
import com.vk.api.sdk.actions.Messages;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by amowel on 06.04.17.
 */
@Component
public class VkApiExtended extends VkApiClient {
    @Autowired
    public VkApiExtended(TransportClient transportClient) {
        super(transportClient);
    }

    @Override
    public MessagesApi messages() {
        return new MessagesApi(this);
    }
}
