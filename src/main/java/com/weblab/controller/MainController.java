package com.weblab.controller;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.configuration.vk.VkAuthHandler;
import com.weblab.model.Token;
import com.weblab.model.UserConnection;
import com.weblab.repository.UserConnectionRepository;
import com.weblab.sources.VkBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
@CrossOrigin
public class MainController {

    @Autowired
    private VkBot vkBot;

    @Autowired
    private VkAuthHandler vkAuthHandler;
    @Qualifier("userConnectionRepository")
    @Autowired
    private UserConnectionRepository userConnectionRepository;


    @RequestMapping(value = {"/", "add-accounts", "new-post"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/vk", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> vk(@RequestBody String json) throws IOException, ClientException, ApiException {
        log.info("Get json from vk: {}", json);
        vkBot.delegate(json);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "generatecode", method = RequestMethod.GET)
    public String status(@RequestParam(value = "code", required = true) String code,
                         ModelMap model, @RequestHeader HttpHeaders headers) throws IOException {
        Token token = vkAuthHandler.authorize(code);
        userConnectionRepository.save(UserConnection.builder().vkId(token.getUserId()).token(token.getAccessToken()).build());
        return "redirect:https://homeless.ngrok.io/";
    }

}
