package com.weblab.controller;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.configuration.vk.VkAuthHandler;
import com.weblab.model.Account;
import com.weblab.model.InstagramConnection;
import com.weblab.model.VkConnection;
import com.weblab.service.dal.AccountDao;
import com.weblab.sources.VkBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Controller
@CrossOrigin
public class MainController {

    @Autowired
    private VkBot vkBot;

    @Autowired
    private VkAuthHandler vkAuthHandler;

    @Autowired
    private AccountDao dao;


    @RequestMapping(value = {"/", "add-accounts", "new-post"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/vk", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> vk(@RequestBody String json) throws IOException, ClientException, ApiException {
        log.info("Get json from vk: {}", json);
        dao.save(Account
                .builder()
                .username("amowel")
                .password("password")
                .created(LocalDate.now())
                .vkConnection(new VkConnection("212372727","test"))
                .instagramConnection(new InstagramConnection("henaltestbot","amazingbot"))
                .build()
        );
        Account account = dao.findByUsername("amowel");
        account.getVkConnection().setAccount(null);
       account.setVkConnection(null);
       dao.save(account);

        vkBot.delegate(json);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @RequestMapping(value = "generatecode", method = RequestMethod.GET)
    public String status(@RequestParam(value = "code", required = true) String code,
                         ModelMap model, @RequestHeader HttpHeaders headers) throws IOException {
        //TODO user authentication
        return "redirect:https://homeless.ngrok.io/";
    }


}
