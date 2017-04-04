package com.weblab.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.callback.objects.CallbackMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.handler.Handler;
import com.weblab.handler.InstagramHandler;
import com.weblab.handler.VkHandler;
import com.weblab.service.basic.JsonParseService;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

/**
 * Created by amowel on 03.04.17.
 */
@Setter
@Service
public class VkBot implements Bot{
    @Autowired
    private JsonParseService parser;
    @Autowired
    private VkHandler vkHandler;
    @Autowired
    private InstagramHandler instagramHandler;
    @Override
    public void delegate(String json) {
        Message message = parser.parseMessage(json);
        if(message.getBody().startsWith("/inst"))
        {
            instagramHandler.handle(json);
        }

        else {

            vkHandler.handle(json);
        }
    }
}
