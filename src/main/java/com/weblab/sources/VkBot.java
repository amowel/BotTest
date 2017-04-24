package com.weblab.sources;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.exceptions.CommandSyntaxException;
import com.weblab.exceptions.InstagramException;
import com.weblab.handler.InstagramHandler;
import com.weblab.handler.VkHandler;
import com.weblab.service.basic.JsonParseService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by amowel on 03.04.17.
 */
@Slf4j
@Setter
@Service
public class VkBot implements Source {
    @Autowired
    private JsonParseService parser;
    @Autowired
    private VkHandler vkHandler;
    @Autowired
    private InstagramHandler instagramHandler;

    private String[] vkCommands = {"say"};
    private String[] instagramCommands = {"login", "logout", "post"};

    @Override
    public void delegate(String json) {
        Message message = parser.parseMessage(json);
        try {

            if (Arrays.stream(instagramCommands).anyMatch((command) -> message.getBody().toLowerCase().startsWith(command))) {
                log.info("Handled by instagramHandler with message " + message.getBody());
                instagramHandler.handle(json);
            } else if (Arrays.stream(vkCommands).anyMatch((command) -> message.getBody().toLowerCase().startsWith(command))) {
                log.info("Handled by vkHandler with message " + message.getBody());
                vkHandler.handle(json);
            } else if (message.getBody().equals("help")) {
                vkHandler.getVkService().sendMessage(message, getHelpText());
            } else {
                vkHandler.getVkService().sendMessage(message, "Command not found. Use \"help\" to get list of available commands");
            }
        } catch (InstagramException | CommandSyntaxException e) {
            log.error("Error while processing request ", e);
            e.setVkMessage(message);
            try {
                vkHandler.getVkService().sendMessage(e.getVkMessage(), e.getFeedBackMessage());

            } catch (ApiException | ClientException clientException) {
                log.error("Can`t send message ", clientException);
            }
        } catch (ClientException | ApiException e) {
            log.error("Can`t send message: {}", e);
        }

    }

    public void sendCallback(Message message, String text) {
        try {
            vkHandler.getVkService().sendMessage(message, text);
        } catch (ApiException | ClientException e) {
            log.error("Can`t send message: {}", e);
        }
    }

    private String getHelpText() {
        String help = "Instagram Commands:\n";
        for (String command : instagramCommands)
            help = help + command + "\n";
        help += "Vk Commands:\n";
        for (String command : vkCommands)
            help = help + command + "\n";
        return help;
    }
}
