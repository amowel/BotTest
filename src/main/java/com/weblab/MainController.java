package com.weblab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.weblab.service.UserServiceImpl;
import com.weblab.service.EmailServiceWrapper;
import com.weblab.service.PollyService;
import com.weblab.vk.VkService;
import javazoom.jl.decoder.JavaLayerException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Controller
@CrossOrigin
public class MainController {

    @Autowired
    EmailServiceWrapper emailServiceWrapper;
    @Autowired
    VkService vk;
    @Autowired
    DataSource dataSource;
    @Autowired
    private UserServiceImpl userDetailService;
    @Autowired
    private PollyService pollyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() throws IOException, JavaLayerException {

        return "home";
    }

    @RequestMapping(value = "/vk", method = RequestMethod.POST, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<String> vk(@RequestBody String json) throws IOException {
        if(new ObjectMapper().readValue(json, JsonNode.class).get("object").get("body").asText().length()<1000)
                vk.send(json);
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

    @RequestMapping(value = "getcode", method = RequestMethod.GET)
    public String status(@RequestParam(value = "code", required = true) String code,
                         ModelMap model) throws IOException {

        return "mainpage";
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
