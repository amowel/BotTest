package com.weblab.handler.impl;

import com.vk.api.sdk.objects.messages.Message;
import com.weblab.exceptions.CommandSyntaxException;
import com.weblab.exceptions.InstagramException;
import com.weblab.handler.Handler;
import com.weblab.model.UserConnection;
import com.weblab.service.InstagramService;
import com.weblab.service.basic.JsonParseService;
import com.weblab.sources.VkBot;
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
    public void handle(String json) throws InstagramException, CommandSyntaxException {

        Message message = parser.parseMessage(json);
        try {
            if (message.getBody().startsWith("login ")) {
                String body = message.getBody();
                String[] credentials = body.replace("login ", "").split(" ");
                if(credentials.length!=2)
                    throw new ArrayIndexOutOfBoundsException("login command should have only two parameters");
                UserConnection connection = UserConnection
                        .builder()
                        .vkId(message.getUserId().longValue())
                        .username(credentials[0])
                        .password(credentials[1])
                        .build();
                instagramService.authorize(connection);
                log.info("User {} logined  in instagram", credentials[0]);
                vkBot.sendCallback(message, "You successfully logined in instagram");
            } else if (message.getBody().startsWith("post ")) {
                instagramService.post(message);
                vkBot.sendCallback(message, "Post successfully created");
            } else if (message.getBody().equals("logout")) {
                instagramService.logout(message.getUserId());
                vkBot.sendCallback(message, "You successfully logged out");
            } else {
                CommandSyntaxException exception = new CommandSyntaxException("Wrong parameters");
                exception.setFeedBackMessage("Your command differs from proposed syntax. Maybe you forget some parameters");
                throw exception;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            CommandSyntaxException exception = new CommandSyntaxException("Wrong parameters",e);
            exception.setFeedBackMessage("Your command differs from proposed syntax. Maybe you forgot some parameters.");
            throw exception;
        }
    }
}
