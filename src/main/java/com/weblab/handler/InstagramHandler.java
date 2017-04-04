package com.weblab.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vk.api.sdk.callback.objects.CallbackMessage;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.service.basic.JsonParseService;
import com.weblab.service.instagram.InstagramService;
import com.weblab.model.UserConnection;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Created by amowel on 03.04.17.
 */
@Slf4j
@Setter
@Service
public class InstagramHandler implements Handler {
    @Autowired
    private InstagramService instagramService;
    @Autowired
    private JsonParseService parser;
    @Override
    public void handle(String  json) {

        Message message = parser.parseMessage(json);
        if(message.getBody().startsWith("/inst auth"))
        {
            String body = message.getBody();
            String[] credentials=body.replace("/inst auth ","").split(" ");
            log.error(Arrays.deepToString(credentials));
            instagramService.authorize(
                    new UserConnection(
                            (long)message.getUserId(),
                            credentials[0],
                            credentials[1]

                    ));

        }
        else if(message.getBody().startsWith("/inst post "))
        {

            instagramService.post(message);
        }
    }
}
