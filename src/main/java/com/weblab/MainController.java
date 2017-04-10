package com.weblab;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.sources.VkBot;
import com.weblab.configuration.vk.VkAuthHandler;
import com.weblab.service.basic.EmailServiceWrapper;
import com.weblab.service.basic.PollyService;
import com.weblab.service.dal.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Slf4j
@Controller
@CrossOrigin
public class MainController {

    @Autowired
    EmailServiceWrapper emailServiceWrapper;

    @Autowired
    private VkBot vkBot;

    @Autowired
    private UserServiceImpl userDetailService;

    @Autowired
    private PollyService pollyService;
    @Autowired
    private VkAuthHandler vkAuthHandler;

    @RequestMapping(value = {"/","add-accounts","new-post"})
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/vk", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> vk(@RequestBody String json) throws IOException, ClientException, ApiException {
        log.info("Get json from vk: {}", json);
        vkBot.delegate(json);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }


    @PostMapping("register")
    public String registration(HttpServletRequest request,
                               @RequestParam(value = "username", required = true) String email,
                               @RequestParam(value = "password", required = true) String password) throws UnsupportedEncodingException {

        System.out.println(email);
//        emailServiceWrapper.sendEmail(email);
        userDetailService.create(email, "", "", password);
        return "";
    }

    @RequestMapping(value = "generatecode", method = RequestMethod.GET)
    public String status(@RequestParam(value = "code", required = true) String code,
                         ModelMap model) throws IOException {
        vkAuthHandler.authorize(code);
         return  "redirect:https://homeless.ngrok.io/";
    }


    @GetMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }

    @PostMapping("audio**")
    @ResponseBody
    public String audio(@RequestBody String text, Principal principal) throws IOException {
        return pollyService.createFile(text, principal.getName()).getName();

    }


}
