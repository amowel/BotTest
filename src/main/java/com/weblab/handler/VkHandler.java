package com.weblab.handler;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.weblab.dal.UserRequestsInfoRepository;
import com.weblab.exceptions.CommandSyntaxException;
import com.weblab.model.UserRequestsInfo;
import com.weblab.service.VkService;
import com.weblab.service.basic.JsonParseService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Created by amowel on 03.04.17.
 */

@Slf4j
@Setter
@Service
public class VkHandler implements Handler {

    @Autowired
    private UserRequestsInfoRepository requestRepository;
    @Getter
    @Autowired
    private VkService vkService;
    @Autowired
    private JsonParseService parser;

    public void handle(String json) throws CommandSyntaxException {
        Message message = parser.parseMessage(json);
        UserRequestsInfo userRequestsInfo = requestRepository.findUserRequestsInfo(message.getUserId());
        if (userRequestsInfo == null) {
            log.info("User with id {} makes first request", message.getUserId());
            userRequestsInfo = requestRepository.saveUserRequestsInfo(new UserRequestsInfo(message.getUserId(), 1));
        }
        try {
            if (requestRepository.isBanned(userRequestsInfo.getVkId())) {
                log.info("User should be banned");
                vkService.sendMessage(message, "You`re timeouted");
            } else {
                if (message.getBody().length() == 3) {
                    CommandSyntaxException exception = new CommandSyntaxException("Wrong parameters");
                    exception.setFeedBackMessage("Your command differs from proposed syntax. Maybe you forgot some parameters");
                    throw exception;
                } else if (message.getBody().length() < 500) {
                    vkService.sendAudio(message);
                } else {

                    vkService.sendMessage(message, MessageFormat.format("Максимальна кількість символів 500. Ви ввели {0}",
                            message.getBody().length()));

                }
                log.info("User made {} requests", userRequestsInfo.getRequestCounter());
                requestRepository.updateUserRequestsInfo(new UserRequestsInfo(userRequestsInfo.getVkId()
                        , userRequestsInfo.getRequestCounter()));

            }
        } catch (ClientException | ApiException e) {
            log.error("Unavailable to send plain text message", e);
        }
    }

}
