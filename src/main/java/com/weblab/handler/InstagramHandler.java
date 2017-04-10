package com.weblab.handler;

import com.vk.api.sdk.objects.messages.Message;
import com.weblab.sources.VkBot;
import com.weblab.exceptions.ImageNotFoundException;
import com.weblab.exceptions.LoginFailedException;
import com.weblab.exceptions.LogoutFailedException;
import com.weblab.model.UserConnection;
import com.weblab.service.basic.JsonParseService;
import com.weblab.service.instagram.InstagramService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amowel on 03.04.17.
 */
@Slf4j
@Setter
@Service
public class InstagramHandler implements Handler {
    @Getter
    @Autowired
    private InstagramService instagramService;
    @Autowired
    private JsonParseService parser;
    @Getter
    @Autowired
    private VkBot vkBot;

    @Override
    public void handle(String json) throws ImageNotFoundException, LoginFailedException, LogoutFailedException {

        Message message = parser.parseMessage(json);
        if (message.getBody().startsWith("login ")) {
            String body = message.getBody();
            String[] credentials = body.replace("login ", "").split(" ");
            instagramService.authorize(
                    new UserConnection(
                            (long) message.getUserId(),
                            credentials[0],
                            credentials[1]

                    ));
            log.info("User {} logined  in instagram", credentials[0]);
            vkBot.sendCallback(message, "You successfully logined in instagram");
        } else if (message.getBody().startsWith("post ")) {
            instagramService.post(message);
            vkBot.sendCallback(message, "Post successfully created");
        } else if (message.getBody().equals("logout")) {
            instagramService.logout(message.getUserId());
            vkBot.sendCallback(message, "You successfully logged out");
        }
    }
}
